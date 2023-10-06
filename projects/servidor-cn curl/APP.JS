const axios = require('axios');

// Define the base URL of your Python server
const baseURL = 'http://localhost:8000';

// Function to perform a GET request
async function doGet() {
  try {
    const response = await axios.get(`${baseURL}/recursos`);
    console.log('GET Response:', response.data);
  } catch (error) {
    console.error('GET Error:', error.response ? error.response.data : error.message);
  }
}

// Function to perform a POST request
async function doPost() {
  const newData = { name: 'New Resource' };
  try {
    const response = await axios.post(`${baseURL}/recursos`, newData);
    console.log('POST Response:', response.data);
  } catch (error) {
    console.error('POST Error:', error.response ? error.response.data : error.message);
  }
}

// Function to perform a PUT request
async function doPut(resourceId, updatedData) {
  try {
    const response = await axios.put(`${baseURL}/recurso/${resourceId}`, updatedData);
    console.log('PUT Response:', response.data);
  } catch (error) {
    console.error('PUT Error:', error.response ? error.response.data : error.message);
  }
}

// Function to perform a DELETE request
async function doDelete(resourceId) {
  try {
    const response = await axios.delete(`${baseURL}/recurso/${resourceId}`);
    console.log('DELETE Response:', response.data);
  } catch (error) {
    console.error('DELETE Error:', error.response ? error.response.data : error.message);
  }
}
