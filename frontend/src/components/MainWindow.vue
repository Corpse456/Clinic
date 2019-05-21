<template>
    <div class="hello">
        <h1>Welcome to Clinic Application</h1>
        <div>Choose doctor speciality:</div>
        <br/>
        <select v-model="selectedSpeciality" v-on:change="addNameSelect">
            <option v-for="speciality in specialities" v-bind:value="speciality" v-bind:key="speciality.id">
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
                dictionary: [],
                specialities: [],
                genders: [],
                gendersMap: {},
                shiftOrder: [],
                shiftOrderMap: {},
                doctors: [],
                selectedSpeciality: "",
                selectedDoctor: "",
                doctorInfo: "",
                show: false
            };
        },
        created() {
            axios.get('/backend/dictionary')
                .then(response => {
                    this.dictionary = response.data;
                    this.specialities = this.dictionary.specialities;

                    this.genders = this.dictionary.genders;
                    this.gendersMap = new Map(this.genders.map(el => [el.value, el.label]));

                    this.shiftOrder = this.dictionary.shiftOrder;
                    this.shiftOrderMap = new Map(this.genders.map(el => [el.value, el.label]));
                });
        },
        methods: {
            addNameSelect: function () {
                this.show = true;
                let searchRequest = {};
                searchRequest.specialityId = this.selectedSpeciality.id;
                axios.post('/backend/doctor/search', searchRequest)
                    .then(response => (this.doctors = response.data.elements));
            },
            addDoctorInfo: function () {
                let year = new Date(this.selectedDoctor.birthDate);
                this.doctorInfo = "Age: " + (new Date().getFullYear() - year.getFullYear())
                    + ", " + this.gendersMap.get(this.selectedDoctor.gender);
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
