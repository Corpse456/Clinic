const state = {
    specialities: [],
    genders: {},
    shiftOrders: {},
    auth: false,
};

const getters = {};

const mutations = {
    init(state, dictionaryData) {
        state.specialities = dictionaryData.specialities;
        state.genders = new Map(dictionaryData.genders.map(el => [el.value, el.label]));
        state.shiftOrders = new Map(dictionaryData.shiftOrders.map(el => [el.value, el.label]));
        state.auth = true;
    }
};

export default {
    namespaced: true,
    state,
    getters,
    mutations
}
