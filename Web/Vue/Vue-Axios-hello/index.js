new Vue({
  el: "#app",
  data () {
    return {
      info: ""
    }
  },
  mounted () {
    axios
      .post("http://localhost:5000/api", {
        query: '{ expense(id:"074d6f32-8072-400a-b84e-b95b65accd21") { amount employee { firstName lastName } } }'
      })
      .then(response => (this.info = response.data.data))
      .catch(error => console.log(`Thats an error: ${error}`))
    }
})


// '{ "query": "{ expense(id:"074d6f32-8072-400a-b84e-b95b65accd21") { amount employee { firstName lastName } } }" }'

// curl -X POST -H "Content-Type: application/json" --data '{ "query": "{ expense(id:\"074d6f32-8072-400a-b84e-b95b65accd21\") { amount employee { firstName lastName } } }" }' http://localhost:5000/api
