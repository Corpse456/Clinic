<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>
    import axios from 'axios';
    import Vue from 'vue';
    import Vuex from 'vuex';
    import VueCookies from 'vue-cookies';

    Vue.use(Vuex);

    export default {
        name: 'app',
        beforeCreate() {
            axios.get('/backend/dictionary', {
                headers: {
                    Authorization: VueCookies.get('authorization')
                }
            }).then(response => {
                if (response.status === 200) {
                    this.$store.commit('dictionary/init', response.data);
                    this.$router.push({name: 'MainWindow'});
                } else {
                    this.$router.push({name: 'Login'});
                }
            }).catch(() => this.$router.push({name: 'Login'}));
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
