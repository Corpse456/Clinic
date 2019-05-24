<template>
    <div class="hello">
        <img alt="Vue logo" src="../assets/Clinic.png">
        <h1>Welcome to Clinic Application</h1>
        <div>Please, login:</div>
        <br/>
        <input v-model="name" placeholder="name"/>
        <br/>
        <input v-model="password" placeholder="password"/>
        <br/>
        <button v-on:click="login">Login</button>
        <br/>
        <button v-on:click="dictionary">Dictionary</button>
        <div>{{dictionaryData}}</div>
    </div>
</template>

<script>
    import axios from 'axios';
    import VueCookies from 'vue-cookies';

    export default {
        data() {
            return {
                token: "",
                name: "",
                password: "",
                dictionaryData: {}
            };
        },
        methods: {
            login() {
                let credentials = {};
                credentials.name = this.name;
                credentials.password = this.password;
                axios.post('/backend/login', credentials)
                    .then(response => {
                        this.token = response.headers.authorization;
                        VueCookies.config('7d');
                        VueCookies.set('authorization', this.token);
                    })
            },
            dictionary() {
                axios.get('/backend/dictionary', {
                    headers: {
                        "Authorization": this.token
                    }
                }).then(response => {
                    this.dictionaryData = response.data;
                })
            }
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
</style>
