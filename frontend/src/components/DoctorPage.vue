<template>
    <div class="doctor">
        <div>Ordered tickets:</div>
        <br/>
        <table>
            <thead>
            <tr>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Date/Time</th>
                <th scope="col">Number</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(ticket, index) in tickets" :key="index">
                <td>{{ticket.patientName}}</td>
                <td>{{ticket.patientLastName}}</td>
                <td>{{ticket.dateTime}}</td>
                <td>{{ticket.number}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import axios from 'axios';
    import {mapState} from 'vuex';
    import VueCookies from 'vue-cookies';

    export default {
        data() {
            return {
                tickets: []
            }
        },
        computed: {
            ...mapState({
                doctorId: state => state.dictionary.doctorId,
            })
        },
        created() {
            let searchRequest = {
                doctorId: this.doctorId
            };
            axios.post('/backend/ticket/search', searchRequest, {
                headers: {
                    Authorization: VueCookies.get('authorization')
                }
            }).then(response => {
                    this.tickets = response.data.elements
                }
            );
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }

    body {
        padding: 3rem;
        font-family: Helvetica Neue, Arial, sans-serif;
        font-size: 14px;
        color: #444;
    }

    table {
        border: 2px solid #42b983;
        border-radius: 3px;
        background-color: #fff;
    }

    th {
        background-color: #42b983;
        color: rgba(255, 255, 255, 0.66);
        cursor: pointer;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    td {
        background-color: #f9f9f9;
    }

    th, td {
        min-width: 120px;
        padding: 10px 20px;
    }
</style>
