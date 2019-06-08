<template>
    <div id="app">
        <button v-if="!login" id="logout" v-on:click="logout">Logout</button>
        <UserPage v-if='user'/>
        <DoctorPage v-if='doctorUser'/>
        <AdminPage v-if='admin'/>
        <Login v-if="login"/>
    </div>
</template>

<script>
    import axios from 'axios';
    import {mapState} from 'vuex';
    import VueCookies from 'vue-cookies';
    import Login from './components/Login.vue'
    import UserPage from './components/UserPage.vue'
    import DoctorPage from './components/DoctorPage.vue'
    import AdminPage from './components/AdminPage.vue'

    export default {
        name: 'app',
        components: {
            Login, UserPage, DoctorPage, AdminPage
        },
        computed: {
            ...mapState({
                user: state => state.dictionary.user,
                admin: state => state.dictionary.admin,
                doctorUser: state => state.dictionary.doctor,
                login: state => state.dictionary.login,
            })
        },
        beforeCreate() {
            if (!this.$store.state.dictionary.specialities.isEmpty) {
                axios.get('/backend/dictionary', {
                    headers: {
                        Authorization: VueCookies.get('authorization')
                    }
                }).then(response => {
                    if (response.status === 200) {
                        this.$store.commit('dictionary/init', response.data);
                    }
                });
            }
        },
        methods: {
            logout() {
                axios.post('/backend/user/logout', {}, {
                    headers: {
                        Authorization: VueCookies.get('authorization')
                    }
                }).then(VueCookies.remove('authorization'));
            }
        }
    }
</script>

<style>
    #logout {
        position: absolute;
        top: 2%;
        right: 2%;
    }

    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
