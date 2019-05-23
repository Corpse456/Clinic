<template>
    <div id="app">
        <img alt="Vue logo" src="./assets/Clinic.png">
        <router-view></router-view>
        <button v-on:click="login">login</button>
    </div>
</template>

<script>
    export default {
        name: 'app',
        methods: {
            login: function () {
                this.$router.push('Login');
            }
        }
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
