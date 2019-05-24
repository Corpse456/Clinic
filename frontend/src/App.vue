<template>
    <div id="app">
        <Login v-if='!auth'/>
        <MainWindow v-else/>
    </div>
</template>

<script>
    import MainWindow from './components/MainWindow.vue'
    import Login from './components/Login.vue'
    import axios from 'axios';
    import VueCookies from 'vue-cookies';

    export default {
        name: 'app',
        components: {
            MainWindow, Login
        },
        data() {
            return {
                auth: false
            }
        },
        created() {
            axios.get('/backend/dictionary', {
                headers: {
                    "Authorization": VueCookies.get('authorization')
                }
            }).then(response => {
                if (response.status === 200) {
                    this.auth = true;
                    this.dictionaryData = response.data;
                }
            });
        }
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
