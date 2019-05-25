const state = {
    specialities: {},
    genders: {},
    shiftOrders: {},
    auth: false,
};

const getters = {};

const mutations = {
    init(state, dictionaryData) {
        this.specialities = dictionaryData.specialities;
        this.genders = new Map(dictionaryData.genders.map(el => [el.value, el.label]));
        this.shiftOrders = new Map(dictionaryData.shiftOrders.map(el => [el.value, el.label]));
        this.auth = true;
    }
};

export default {
    namespaced: true,
    state,
    getters,
    mutations
}
