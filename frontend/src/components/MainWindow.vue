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
        mounted() {
            axios
                .get('http://localhost:8090/clinic/api/dictionary')
                .then(response => (this.dictionary = response.data));
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
