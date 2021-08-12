// Display/block API Key input field based on the API Key switch toggle
const apiKeyDiv = document.getElementById('apiKeyInput');
const apiKeySwitch = document.getElementById('marsRoverApiKeySwitch');

// Executes on page load
function setUp() {
    apiKeyDiv.style.display = apiKeySwitch.checked ? 'none' : 'block'
}

// Handles API Key switch toggle
apiKeySwitch.onclick = () => apiKeyDiv.style.display = apiKeySwitch.checked ? 'none' : 'block';
