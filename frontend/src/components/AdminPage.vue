<template>
    <div class="admin">
        <button v-on:click="fieldVisibility('createDoctor')">Hire doctor</button>
        <button v-on:click="fieldVisibility('deleteDoctor')">Fire doctor</button>
        <button v-on:click="fieldVisibility('createPatient')">Create patient card</button>
        <button v-on:click="fieldVisibility('createAdmin')">Create new administrator</button>
        <br/>
        <button v-on:click="fillDoctorsTable()">Show all doctors</button>
        <button v-on:click="fillPatientsTable()">Show all patient</button>
        <button v-on:click="fillTicketsTable()">Show all tickets</button>

        <input v-if="isCreateAdmin" v-model="alias" type="text" placeholder="alias"/>
        <input v-if="isCreateAdmin" v-model="password" type="password" placeholder="password"/>
        <input v-if="isDeleteDoctor" v-model="id" type="text" placeholder="id"/>
        <input v-if="isCreateDoctor || isCreatePatient || isCreateAdmin" v-model="name" type="text" placeholder="name"/>
        <input v-if="isCreateDoctor || isCreatePatient || isCreateAdmin" v-model="lastName" type="text"
               placeholder="last name"/>
        <input v-if="isCreateDoctor || isCreatePatient" v-model="birthDate" type="date"
               placeholder="birth date"/>
        <select v-if="isCreateDoctor || isCreatePatient" v-model="selectedGender">
            <option v-for="gender in genders" v-bind:value="gender" v-bind:key="gender.value">
                {{ gender[1] }}
            </option>
        </select>
        <select v-if="isCreateDoctor" v-model="selectedSpeciality">
            <option v-for="speciality in specialities" v-bind:value="speciality" v-bind:key="speciality.id">
                {{ speciality.name }}
            </option>
        </select>
        <button v-if="isCreateDoctor || isDeleteDoctor || isCreatePatient || isCreateAdmin" v-on:click="submit">Submit</button>

        <table v-if="doctors.length > 0">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Gender</th>
                <th scope="col">Birth Date</th>
                <th scope="col">Speciality</th>
                <th scope="col">Special Identifier</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(doctor, index) in doctors" :key="index">
                <td>{{doctor.id}}</td>
                <td>{{doctor.name}}</td>
                <td>{{doctor.lastName}}</td>
                <td>{{genders.get(doctor.gender)}}</td>
                <td>{{doctor.birthDate}}</td>
                <td>{{getSpecialityName(doctor.specialityId)}}</td>
                <!--TODO find out how show it-->
                <td>{{doctor.specialIdentifier}}</td>
            </tr>
            </tbody>
        </table>
        <table v-if="patients.length > 0">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Gender</th>
                <th scope="col">Birth Date</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(patient, index) in patients" :key="index">
                <td>{{patient.id}}</td>
                <td>{{patient.name}}</td>
                <td>{{patient.lastName}}</td>
                <td>{{genders.get(patient.gender)}}</td>
                <td>{{patient.birthDate}}</td>
            </tr>
            </tbody>
        </table>
        <table v-if="tickets.length > 0">
            <thead>
            <tr>
                <th scope="col">Patient ID</th>
                <th scope="col">Patient First Name</th>
                <th scope="col">Patient Last Name</th>
                <th scope="col">Date/Time</th>
                <th scope="col">Number</th>
                <th scope="col">Doctor ID</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(ticket, index) in tickets" :key="index">
                <td>{{ticket.patientId}}</td>
                <td>{{ticket.patientName}}</td>
                <td>{{ticket.patientLastName}}</td>
                <td>{{ticket.dateTime}}</td>
                <td>{{ticket.number}}</td>
                <td>{{ticket.doctorId}}</td>
            </tr>
            </tbody>
        </table>
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
                isDeleteDoctor: false,
                isCreatePatient: false,
                isCreateAdmin: false,
                selectedGender: "",
                selectedSpeciality: "",
                doctors: [],
                patients: [],
                tickets: [],
                id: "",
                name: "",
                lastName: "",
                birthDate: "",
                alias: "",
                password: "",
            }
        },
        computed: {
            ...mapState({
                genders: state => state.dictionary.genders,
                specialities: state => state.dictionary.specialities,
            })
        },
        methods: {
            toast(message) {
                if (Array.isArray(message)) {
                    message.forEach(m => this.toast(m))
                } else {
                    this.$toasted.show(message.message ? message.message : message, {
                        theme: "outline",
                        position: "top-right",
                        duration: 5000
                    })
                }
            },
            getSpecialityName(specialityId) {
                return new Map(this.specialities.map(el => [el.id, el.name])).get(specialityId);
            },
            fillDoctorsTable() {
                axios.post('/backend/admin/doctor/search', {}, {
                    headers: {
                        Authorization: VueCookies.get('authorization')
                    }
                }).then(response => {
                    this.doctors = response.data.elements;
                    this.fieldVisibility("showDoctors")
                }).catch(error => this.toast(error.response.data));
            },
            fillPatientsTable() {
                axios.post('/backend/patient/search', {}, {
                    headers: {
                        Authorization: VueCookies.get('authorization')
                    }
                }).then(response => {
                    this.patients = response.data.elements;
                    this.fieldVisibility("showPatients")
                }).catch(error => this.toast(error.response.data));
            },
            fillTicketsTable() {
                axios.post('/backend/ticket/search', {}, {
                    headers: {
                        Authorization: VueCookies.get('authorization')
                    }
                }).then(response => {
                    this.tickets = response.data.elements;
                    this.fieldVisibility("showTickets")
                }).catch(error => this.toast(error.response.data));
            },
            fieldVisibility(button) {
                this.isDeleteDoctor = button === "deleteDoctor";
                this.isCreateDoctor = button === "createDoctor";
                this.isCreatePatient = button === "createPatient";
                this.isCreateAdmin = button === "createAdmin";
                if (button !== "showDoctors") {
                    this.doctors = [];
                }
                if (button !== "showPatients") {
                    this.patients = [];
                }
                if (button !== "showTickets") {
                    this.tickets = [];
                }
            },
            submit() {
                function getTwoZeroes(value) {
                    return value < 10 ? '0' + value : value;
                }

                if (this.isDeleteDoctor) {
                  axios.delete('/backend/doctor/' + this.id, {
                    headers: {
                      Authorization: VueCookies.get('authorization')
                    }
                  }).then(() => {
                    this.toast("Done");
                    this.fieldVisibility()
                  }).catch(error => this.toast(error.response.data));
                } else if (this.isCreateDoctor) {
                    let date = new Date(this.birthDate);
                    if (date.length === 0 || this.name === 0 || this.lastName === 0 || this.gender === 0 || this.selectedSpeciality === 0) {
                        this.toast("Form is not ready");
                        return;
                    }
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
                    }).then(() => {
                        this.toast("Done");
                        this.fieldVisibility()
                    }).catch(error => this.toast(error.response.data));
                } else if (this.isCreatePatient) {
                    let date = new Date(this.birthDate);
                    if (date.length === 0 || this.name === 0 || this.lastName === 0 || this.gender === 0) {
                        this.toast("Form is not ready");
                        return;
                    }
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
                    }).then(() => {
                        this.toast("Done");
                        this.fieldVisibility()
                    }).catch(error => this.toast(error.response.data));
                } else if (this.isCreateAdmin) {
                    if (this.alias.length === 0 || this.password === 0 || this.lastName === 0 || this.lastName === 0) {
                        this.toast("Form is not ready");
                        return;
                    }
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
                    }).then(() => {
                        this.toast("Done");
                        this.fieldVisibility()
                    }).catch(error => this.toast(error.response.data));
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

    input {
        display: block;
        margin: 4px auto;
    }

    select {
        display: block;
        margin: 4px auto;
    }

    button {
        margin: 4px;
    }

    body {
        padding: 3rem;
    }

    table {
        border: 2px solid #42b983;
        border-radius: 3px;
        background-color: #fff;
    }

    th {
        background-color: #42b983;
        color: rgba(255, 255, 255, 0.66);
        cursor: pointer;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    td {
        background-color: #f9f9f9;
    }

    th, td {
        min-width: 120px;
        padding: 10px 20px;
    }
</style>
