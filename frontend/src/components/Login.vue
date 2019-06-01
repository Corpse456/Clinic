<template>
    <div class="login">
        <img alt="Vue logo" src="../assets/Clinic.png">
        <h1>Welcome to Clinic Application</h1>
        <div>Please, {{buttonName().toLowerCase()}} or
            <button v-on:click="signUp">{{buttonReverseName()}}</button>
        </div>
        <br/>
        <input v-model="alias" placeholder="nick name"/>
        <br/>
        <input type="password" v-model="password" placeholder="password"/>
        <br/>
        <input v-if="!isLogin" v-model="name" placeholder="name"/>
        <br/>
        <input v-if="!isLogin" v-model="lastName" placeholder="last name"/>
        <br/>
        <span v-if="!isLogin">
            <input type="checkbox" v-model="checked"><label>As a doctor</label>
        </span>
        <br/>
        <input v-if="checked" v-model="doctorIdentifier" placeholder="Doctor identifier"/>
        <br/><br/>

        <button v-on:click="login">{{buttonName()}}</button>
    </div>
</template>

<script>
    import axios from 'axios';
    import VueCookies from 'vue-cookies';

    export default {
        data() {
            return {
                checked: false,
                alias: "",
                password: "",
                name: "",
                lastName: "",
                doctorIdentifier: "",
                isLogin: true,
                dictionaryData: {}
            };
        },
        methods: {
            login() {
                let credentials = {
                    alias: this.alias,
                    password: this.password,
                    name: this.name,
                    lastName: this.lastName,
                    specialIdentifier: this.doctorIdentifier
                };

                function addAuthorization(response) {
                    let authorization = response.headers.authorization;

                    VueCookies.config('7d');
                    VueCookies.set('authorization', authorization);

                    axios.get('/backend/dictionary', {
                        headers: {
                            Authorization: authorization
                        }
                    }).then(response => {
                        this.$store.commit('dictionary/init', response.data);
                    });
                }

                if (this.isLogin) {
                    axios.post('/backend/login', credentials)
                        .then(response => {
                            if (response.status === 200) {
                                addAuthorization(response);
                            }
                        })
                } else {
                    axios.post('/backend/public/user', credentials)
                        .then(response => {
                            if (response.status === 200) {
                                addAuthorization(response);
                            }
                        })
                }
            },
            signUp() {
                this.isLogin = !this.isLogin;
            },
            buttonName() {
                return this.isLogin ? "Login" : "SignUp";
            },
            buttonReverseName() {
                return this.isLogin ? "SignUp" : "Login";
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
