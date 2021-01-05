function on_response(response) {
  this.expenses = response.data.data.expenses
  // alert(`Thats a problem:\n\n${JSON.stringify(response.data.data.expenses)}`)
}

function on_error(error) {
  console.log(`Thats an error: ${error}`)
  alert(`Thee error: ${error}`)
}

new Vue({
  el: "#app",
  data() {
    return {
      expenses: "",
    }
  },
  mounted() {
    axios
      .post("http://localhost:5000/api", {
        // query: `{ expense(id:"684a1482-ce20-4e74-8df2-0bf4af89fd09") { amount employee { firstName lastName } } }`
        query: `
               {
                 expenses {
                   id
                   description
                   createdAt
                   amount
                   currency
                   state
                   employee {
                     id
                     firstName
                     lastName
                   }
                 }
               }
               `
      })
      .then(on_response.bind(this))
      .catch(on_response.bind(this))
  }
})



    // axios.post({
    //   url: "http://localhost:5000/api",
    //   data: {
    //     query: `
    //       {
    //         expenses {
    //           id
    //           employee {
    //             firstName
    //             lastName
    //           }
    //         }
    //       }
    //       `
    //   }
    // })
//       .then(this.on_received)
//       .catch(this.on_error)
//   }
// })



// axios({
//   url: "http://localhost:5000/api",
//   method: "post",
//   data: {
//     query: `
//       query expenses {
//         id
//         employee {
//           firstName
//           lastName
//             }
//           }
//         }
//       `
//   }
// }).then((result) => {
//   console.log(result.data)
// });

// '{ "query": "{ expense(id:"074d6f32-8072-400a-b84e-b95b65accd21") { amount employee { firstName lastName } } }" }'

// curl -X POST -H "Content-Type: application/json" --data '{ "query": "{ expense(id:\"074d6f32-8072-400a-b84e-b95b65accd21\") { amount employee { firstName lastName } } }" }' http://localhost:5000/api
