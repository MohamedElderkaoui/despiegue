# Use the official Node.js image as a parent image
FROM node:14

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy your Node.js application files to the container
COPY . .

# Expose the port your Node.js application will run on
EXPOSE 3000

# Start your Node.js application
CMD ["node", "your-app.js"]
