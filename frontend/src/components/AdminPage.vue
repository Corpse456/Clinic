<template>
    <div class="admin">
        <button v-on:click="createDoctor">Hire doctor</button>&thinsp;
        <button v-on:click="createPatient">Create patient card</button>&thinsp;
        <button v-on:click="createAdmin">Create new administrator</button>
        <br/><br><input v-if="isCreateAdmin" v-model="alias" type="text" placeholder="alias"/><br/><br/>
        <br/><br><input v-if="isCreateAdmin" v-model="password" type="password" placeholder="password"/><br/><br/>
        <input v-if="isCreateDoctor || isCreatePatient || isCreateAdmin" v-model="name" type="text" placeholder="name"/><br/><br/>
        <input v-if="isCreateDoctor || isCreatePatient || isCreateAdmin" v-model="lastName" type="text"
               placeholder="last name"/><br/><br/>
        <input v-if="isCreateDoctor || isCreatePatient" v-model="birthDate" type="date"
               placeholder="birth date"/><br/><br/>
        <select v-if="isCreateDoctor || isCreatePatient" v-model="selectedGender">
            <option v-for="gender in genders" v-bind:value="gender" v-bind:key="gender.value">
                {{ gender[1] }}
            </option>
        </select><br/><br/>
        <select v-if="isCreateDoctor" v-model="selectedSpeciality">
            <option v-for="speciality in specialities" v-bind:value="speciality" v-bind:key="speciality.id">
                {{ speciality.name }}
            </option>
        </select><br/><br/>
        <button v-if="isCreateDoctor || isCreatePatient || isCreateAdmin" v-on:click="submit">Submit</button>
    </div>
</template>

<script>
    import VueCookies from 'vue-cookies';
    import {mapState} from 'vuex';
    import axios from 'axios';

    export default {
        data() {
            return {
                isCreateDoctor: false,
                isCreatePatient: false,
                isCreateAdmin: false,
                selectedGender: "",
                selectedSpeciality: "",
                name: "",
                lastName: "",
                birthDate: "",
                alias: "",
                password: "",
                tickets: []
            }
        },
        computed: {
            ...mapState({
                genders: state => state.dictionary.genders,
                specialities: state => state.dictionary.specialities,
            })
        },
        methods: {
            createDoctor() {
                this.isCreateDoctor = true;
                this.isCreatePatient = false;
                this.isCreateAdmin = false;
            },
            createPatient() {
                this.isCreateDoctor = false;
                this.isCreatePatient = true;
                this.isCreateAdmin = false;
            },
            createAdmin() {
                this.isCreateDoctor = false;
                this.isCreatePatient = false;
                this.isCreateAdmin = true;
            },
            submit() {
                function getTwoZeroes(value) {
                    return value < 10 ? '0' + value : value;
                }

                if (this.isCreateDoctor) {
                    let date = new Date(this.birthDate);
                    let newDoctor = {
                        name: this.name,
                        lastName: this.lastName,
                        birthDate: date.getFullYear() + "." + getTwoZeroes(date.getMonth() + 1) + "." + getTwoZeroes(date.getDate()),
                        gender: this.selectedGender[0],
                        specialityId: this.selectedSpeciality.id
                    };
                    axios.post('/backend/admin/doctor', newDoctor, {
                        headers: {
                            Authorization: VueCookies.get('authorization')
                        }
                    });
                } else if (this.isCreatePatient) {
                    let date = new Date(this.birthDate);
                    let newPatient = {
                        name: this.name,
                        lastName: this.lastName,
                        birthDate: date.getFullYear() + "." + getTwoZeroes(date.getMonth() + 1) + "." + getTwoZeroes(date.getDate()),
                        gender: this.selectedGender[0],
                    };
                    axios.post('/backend/admin/patient', newPatient, {
                        headers: {
                            Authorization: VueCookies.get('authorization')
                        }
                    });
                } else if (this.isCreateAdmin) {
                    let newAdmin = {
                        alias: this.alias,
                        password: this.password,
                        name: this.name,
                        lastName: this.lastName,
                    };
                    axios.post('/backend/admin/user', newAdmin, {
                        headers: {
                            Authorization: VueCookies.get('authorization')
                        }
                    });
                }
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

    body {
        padding: 3rem;
    }
</style>
