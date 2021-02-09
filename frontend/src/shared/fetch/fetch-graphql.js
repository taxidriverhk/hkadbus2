import axios from 'axios';

export default function fetchGraphQL(query, variables = {}) {
  return axios({
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    url: '/graphql',
    data: {
      query,
      variables
    },
    transformResponse: axios.defaults.transformResponse.concat((data) => {
      return data.data;
    })
  });
}