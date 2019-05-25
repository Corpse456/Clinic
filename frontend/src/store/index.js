import Vue from 'vue'
import Vuex from 'vuex'
import dictionary from './modules/dictionary'

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        dictionary
    },
});