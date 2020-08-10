<template>
  <!-- That's the HTML Body Template -->
  <div id="app">
    Vars:<br>
    {{ message }}
    <br><br><br>

    Input:<br>
    <input v-model="input" placeholder="edit me">
    <p>Message is: {{ input }}</p>
    <br><br><br>
  </div>

</template>

<script>
// Define App variables here
export default {
  name: 'app',
  data () {
    return {
      message: 'Hello Vue!',
      input: ''
    }
  }
}
</script>