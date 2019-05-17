<template>
    <div class="hello">
        <h1>Welcome to Clinic Application</h1>
        <!--<select>
            <option v-for="speciality in dictionary.specialities" v-bind:value="speciality.value">
                {{ speciality.label }}
            </option>
        </select>-->
        <div>
            {{ dictionary }}
        </div>
        <ul>
            <li>first</li>
            <li v-for="(value, key) in dictionary">{{ key }}: {{ value }}</li>
        </ul>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                dictionary: []
            };
        },
        created() {

            axios
                .get('http://localhost:8090/clinic/api/dictionary')
                .then(response => {
                    debugger;
                    (this.dictionary = response.data);
                }).catch(err => {
                debugger;
            });
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
