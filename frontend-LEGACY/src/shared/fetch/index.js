import axios from 'axios';

const cache = {};

async function getFetchConfig() {
  if (cache.config) {
    return cache.config;
  }
  const { data } = await axios({
    method: 'GET',
    url: '/config/fetch-config.json',
  });

  cache.config = data;
  return data;
}

export async function fetchGet(url, params) {
  const { apiBaseUrl } = await getFetchConfig();
  return axios({
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    },
    url: `${apiBaseUrl}${url}`,
    params,
  });
}

export default async function fetch(url, data) {
  const { apiBaseUrl } = await getFetchConfig();
  return axios({
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    url: `${apiBaseUrl}${url}`,
    data,
  });
}
