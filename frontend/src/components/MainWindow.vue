<template>
    <div class="hello">
        <div>Choose doctor:</div>
        <br/>
        <select v-model="selectedSpeciality" v-on:change="addNameSelect">
            <option v-for="speciality in specialities" v-bind:value="speciality"
                    v-bind:key="speciality.id">
                {{ speciality.name }}
            </option>
        </select>
        <br/><br/><br/>
        <select v-model="selectedDoctor" v-if="doctors.length > 0" v-on:change="addDoctorInfo">
            <option v-for="doctor in doctors" v-bind:value="doctor" v-bind:key="doctor.id">
                {{ doctor.name + " " + doctor.lastName}}
            </option>
        </select>
        <br/><br/><br/>
        <div>{{doctorInfo}}</div>
        <br/>
        <select v-model="selectedDay" v-if="shifts.length > 0" v-on:change="addTimeSelect">
            <option v-for="day in shifts" v-bind:value="day" v-bind:key="day.id">
                {{ day.date}}
            </option>
        </select>
        <br/>
        <select v-model="selectedTime" v-if="freeTimes.length > 0">
            <option v-for="time in freeTimes" v-bind:value="time">
                {{ time.getHours() + ":" + getTwoZeroes(time.getMinutes()) + ":" + getTwoZeroes(time.getSeconds())}}
            </option>
        </select>
        <br/><br/><br/>
        <button v-if="selectedTime" v-on:click="orderTicket">Order ticket</button>
    </div>
</template>

<script>
    import axios from 'axios';
    import VueCookies from 'vue-cookies';
    import {mapState} from 'vuex';

    export default {
        data() {
            return {
                doctors: [],
                shifts: [],
                freeTimes: [],
                selectedSpeciality: "",
                selectedDoctor: "",
                selectedDay: "",
                selectedTime: "",
                doctorInfo: "",
            };
        },
        computed: {
            ...mapState({
                specialities: state => state.dictionary.specialities,
                genders: state => state.dictionary.genders
            })
        },
        methods: {

            getTwoZeroes(value) {
                return value < 10 ? '0' + value : value;
            },
            addNameSelect() {
                this.doctorInfo = "";
                this.selectedTime = "";
                this.shifts = [];
                this.freeTimes = [];
                let searchRequest = {
                    specialityId: this.selectedSpeciality.id
                };
                axios.post('/backend/doctor/search', searchRequest, {
                    headers: {
                        Authorization: VueCookies.get('authorization')
                    }
                }).then(response => (this.doctors = response.data.elements));
            },
            addDoctorInfo() {
                this.freeTimes = [];
                let year = new Date(this.selectedDoctor.birthDate);
                this.doctorInfo = "Age: " + (new Date().getFullYear() - year.getFullYear())
                    + ", " + this.genders.get(this.selectedDoctor.gender);
                let searchRequest = {
                    doctorId: this.selectedDoctor.id
                };
                axios.post('/backend/shift/search', searchRequest, {
                    headers: {
                        Authorization: VueCookies.get('authorization')
                    }
                }).then(response => {
                        this.shifts = response.data.elements
                    }
                );
            },
            addTimeSelect() {
                let searchRequest = {
                    doctorId: this.selectedDoctor.id,
                    from: this.selectedDay.date + " " + this.selectedDay.shiftTiming.startTime,
                    to: this.selectedDay.date + " " + this.selectedDay.shiftTiming.endTime
                };

                axios.post('/backend/ticket/search', searchRequest, {
                    headers: {
                        Authorization: VueCookies.get('authorization')
                    }
                }).then(response => {
                        this.freeTimes = [];
                        const busyTimes = response.data.elements.map(t => t.dateTime);
                        let startTime = new Date(searchRequest.from);
                        let endTime = new Date(searchRequest.to);

                        function isContains(time, busyTimes) {
                            for (let i = 0; i < busyTimes.length; i++) {
                                if (new Date(busyTimes[i]).getTime() === time.getTime()) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        while (startTime < endTime) {
                            if (!isContains(startTime, busyTimes)) {
                                this.freeTimes.push(startTime);
                            }
                            startTime = new Date(startTime.getTime() + 15 * 60000);
                        }
                    }
                );
            },
            orderTicket() {
                function getTwoZeroes(value) {
                    return value < 10 ? '0' + value : value;
                }

                let ticket = {
                    patientId: 1, //TODO temporary
                    doctorId: this.selectedDoctor.id,
                    dateTime: this.selectedDay.date
                        + " " + getTwoZeroes(this.selectedTime.getHours())
                        + ":" + getTwoZeroes(this.selectedTime.getMinutes())
                        + ":" + getTwoZeroes(this.selectedTime.getSeconds())
                };
                axios.post('/backend/ticket', ticket, {
                    headers: {
                        Authorization: VueCookies.get('authorization')
                    }
                });
                this.shifts = [];
                this.freeTimes = [];
                this.selectedDoctor = "";
                this.doctorInfo = "";
                this.selectedTime = "";
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }

    select::-ms-expand {
        display: none; /* hide the default arrow in ie10 and ie11 */
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

    select {
        font-size: 16px;
        font-family: sans-serif;
        font-weight: 700;
        color: #444;
        line-height: 1.3;
        padding: .6em 1.4em .5em .8em;
        box-sizing: border-box;
        margin: 0;
        border: 1px solid #aaa;
        box-shadow: 0 1px 0 1px rgba(0, 0, 0, .04);
        border-radius: .5em;
        -moz-appearance: none;
        -webkit-appearance: none;
        appearance: none;
        background-color: #fff;
        background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23007CB2%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E'),
        linear-gradient(to bottom, #ffffff 0%, #e5e5e5 100%);
        background-repeat: no-repeat, repeat;
        background-position: right .7em top 50%, 0 0;
        background-size: .65em auto, 100%;
    }

    select::-ms-expand {
        display: none;
    }

    select:hover {
        border-color: #888;
    }

    select:focus {
        border-color: #aaa;
        box-shadow: 0 0 1px 3px rgba(59, 153, 252, .7);
        box-shadow: 0 0 0 3px -moz-mac-focusring;
        color: #222;
        outline: none;
    }

    .select option {
        font-weight: normal;
    }

    body {
        padding: 3rem;
    }
</style>
