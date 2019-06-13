import VueCookies from 'vue-cookies';
import axios from 'axios';

const state = {
    specialities: [],
    genders: {},
    shiftOrders: {},
    auth: false,
    userId: "",
    patientId: "",
    doctorId: "",
    admin: "",
    user: "",
    doctor: "",
    login: true
};

const getters = {};

const mutations = {
    init(state, dictionaryData) {
        state.specialities = dictionaryData.specialities;
        state.genders = new Map(dictionaryData.genders.map(el => [el.value, el.label]));
        state.shiftOrders = new Map(dictionaryData.shiftOrders.map(el => [el.value, el.label]));
        state.auth = true;

        let authorizationToken = VueCookies.get('authorization');
        const arrayOfStrings = authorizationToken.split(".");
        const idBase64 = arrayOfStrings[1];

        var tokenData = JSON.parse(atob(idBase64));
        const tokenSub = tokenData.sub;
        let strings = tokenSub.split(":");
        state.userId = strings[1];
        state.userRole = strings[2];

        state.login = false;
        switch (state.userRole) {
            case("USER"): {
                state.user = true;
                break;
            }
            case("ADMIN"): {
                state.admin = true;
                break;
            }
            case("DOCTOR"): {
                state.doctor = true;
                break;
            }
        }

        axios.get('/backend/user/' + state.userId, {
            headers: {
                Authorization: authorizationToken
            }
        }).then(response => {
            state.patientId = response.data.patientId;
            state.doctorId = response.data.doctorId;
        });
    },
    logout() {
        state.login = true;
        state.user = false;
        state.admin = false;
        state.doctor = false;
    }
};

export default {
    namespaced: true,
    state,
    getters,
    mutations
}
