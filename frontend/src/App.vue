<template>
    <div id="app">
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
