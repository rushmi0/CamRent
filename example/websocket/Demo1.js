import WebSocket from 'ws'

// User A
const socketA = new WebSocket('ws://localhost:8080/chat/topic/A');

socketA.on('open', () => {
    console.log('WebSocket Connection Opened (A)');
    socketA.send('Hello from A!');
});

socketA.on('message', (data) => {
    console.log('WebSocket Message Received (A):', data);
});

// User B
const socketB = new WebSocket('ws://localhost:8080/chat/topic/B');

socketB.on('open', () => {
    console.log('WebSocket Connection Opened (B)');
    socketB.send('Hello from B!');
});

socketB.on('message', (data) => {
    console.log('WebSocket Message Received (B):', data);
});

// Send messages from A to B
setTimeout(() => {
    socketA.send('Message from A to B');
}, 2000);

// Send messages from B to A
setTimeout(() => {
    socketB.send('Message from B to A');
}, 4000);
