<template>
    <div class="hello">
        <div>Choose doctor speciality:</div>
        <br/>
        <select v-model="selectedSpeciality" v-on:change="addNameSelect">
            <option v-for="speciality in this.$store.state.dictionary.specialities" v-bind:value="speciality"
                    v-bind:key="speciality.id">
                {{ speciality.name }}
            </option>
        </select>
        <br/><br/><br/>
        <select v-model="selectedDoctor" v-if="show" v-on:change="addDoctorInfo">
            <option v-for="doctor in doctors" v-bind:value="doctor" v-bind:key="doctor.id">
                {{ doctor.name + " " + doctor.lastName}}
            </option>
        </select>
        <br/><br/><br/>
        <div>{{doctorInfo}}</div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                doctors: [],
                specialities: {},
                selectedSpeciality: "",
                selectedDoctor: "",
                doctorInfo: "",
                show: false
            };
        },
        created() {
            console.log(this.$store.state.dictionary.specialities)
        },
        methods: {
            addNameSelect() {
                this.show = true;
                let searchRequest = {};
                searchRequest.specialityId = this.selectedSpeciality.id;
                axios.post('/backend/doctor/search', searchRequest)
                    .then(response => (this.doctors = response.data.elements));
            },
            addDoctorInfo() {
                let year = new Date(this.selectedDoctor.birthDate);
                this.doctorInfo = "Age: " + (new Date().getFullYear() - year.getFullYear())
                    + ", " + this.$store.state.dictionary.genders.get(this.selectedDoctor.gender);
            }
        },

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
