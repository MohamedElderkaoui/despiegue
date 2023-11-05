const redis = require('redis');
const client = redis.createClient({ host: 'redis', port: 6379 }); // Use the hostname 'redis' to connect to the Redis container

function connectToRedis() {
    return new Promise((resolve, reject) => {
        client.on('connect', () => {
            console.log('Connected to Redis');
            resolve();
        });

        client.on('error', (err) => {
            console.error('Redis connection error:', err);
            reject(err);
        });
    });
}

async function main() {
    try {
        await connectToRedis();
        console.log('Connected to Redis. Now you can use Redis operations here.');

        // Your code here after successful connection
    } catch (err) {
        // Handle the error
        console.error(`Error occurred while connecting to Redis: ${err}`);
    }
}

main();
