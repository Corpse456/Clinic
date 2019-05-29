<template>
    <div id="app">
        <UserPage v-if='userCheck'/>
        <DoctorPage v-else-if='doctorCheck'/>
        <AdminPage v-else-if='adminCheck'/>
        <Login v-else/>
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
                userRole: state => state.dictionary.userRole
            }),
            userCheck() {
                return this.userRole === "USER";
            },
            doctorCheck() {
                return this.userRole === "DOCTOR";
            },
            adminCheck() {
                return this.userRole === "ADMIN";
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
