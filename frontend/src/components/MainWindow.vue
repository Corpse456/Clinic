<template>
    <div class="hello">
        <h1>Welcome to Clinic Application</h1>
        <div>Choose doctor speciality:</div>
        <br/>
        <select v-model="selectedSpeciality" v-on:change="addNameSelect">
            <option v-for="speciality in specialities" v-bind:key="speciality.id">
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
                shiftOrder: [],
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
                    const tempGenders = {};
                    this.genders = this.genders.forEach(g => {
                        tempGenders[g.value] = g.label;
                    });
                    this.genders = tempGenders;
                    this.shiftOrder = this.dictionary.shiftOrder;
                });
        },
        methods: {
            addNameSelect: function () {
                this.show = true;
                axios.get('/backend/doctor/search')
                    .then(response => (this.doctors = response.data));
            },
            addDoctorInfo: function () {
                let year = new Date(this.selectedDoctor.birthDate);

                function getGenderLabel(gender) {
                    var label = "";
                    this.genders.forEach(g => {
                        if (g.value === gender) {
                            label = g.label;
                        }
                    })
                    return label;
                }

                this.doctorInfo = "Age: " + (new Date().getFullYear() - year.getFullYear())
                    + ", " + getGenderLabel(this.selectedDoctor.gender);
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
