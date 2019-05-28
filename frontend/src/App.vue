<template>
    <div id="app">
        <Login v-if='page() === login'/>
        <UserPage v-if='page() === user'/>
        <DoctorPage v-if='page() === doctor'/>
        <AdminPage v-if='page() === admin'/>
    </div>
</template>

<script>
    import axios from 'axios';
    import Vue from 'vue';
    import Vuex, {mapState} from 'vuex';
    import VueCookies from 'vue-cookies';
    import Login from './components/Login.vue'
    import UserPage from './components/UserPage.vue'
    import DoctorPage from './components/DoctorPage.vue'
    import AdminPage from './components/AdminPage.vue'

    Vue.use(Vuex);

    export default {
        name: 'app',
        components: {
            Login, UserPage, DoctorPage, AdminPage
        },
        data() {
            return {
                user: "user",
                doctor: "doctor",
                admin: "admin",
                login: "login",
            };
        },
        computed: {
            ...mapState({
                userRole: state => state.dictionary.userRole
            })
        },
        methods: {
            page() {
                switch (this.userRole) {
                    case "USER" : {
                        return this.user;
                    }
                    case "DOCTOR" : {
                        return this.doctor;
                    }
                    case "ADMIN" : {
                        return this.admin;
                    }
                    default : {
                        return this.login;
                    }
                }
            }
        },
        beforeCreate() {
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
