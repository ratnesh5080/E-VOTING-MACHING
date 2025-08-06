// === Helper Function: API Call ===
async function apiCall(url, method = 'GET', data = null) {
    const options = {
        method: method,
        headers: { 'Content-Type': 'application/json' }
    };
    if (data) options.body = JSON.stringify(data);

    const response = await fetch(url, options);
    const result = await response.json();
    if (!response.ok) {
        throw new Error(result.message || 'API Error');
    }
    return result;
}

// === Registration Form ===
document.getElementById('registerForm')?.addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = {
        name: document.getElementById('name').value.trim(),
        email: document.getElementById('email').value.trim(),
        password: document.getElementById('password').value.trim(),
        role: document.getElementById('role').value
    };
    try {
        const res = await apiCall('/api/auth/register', 'POST', data);
        alert(`Registration successful! OTP sent to your email.`);
        window.location.href = '/login';   // <-- Fixed redirect
    } catch (error) {
        alert('Registration failed: ' + error.message);
    }
});

// === Login Form ===
document.getElementById('loginForm')?.addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = {
        email: document.getElementById('email').value.trim(),
        password: document.getElementById('password').value.trim()
    };
    try {
        const res = await apiCall('/api/auth/login', 'POST', data);

        // Redirect based on role (NO .html extension)
        if (res.role === 'ADMIN') window.location.href = '/admin-dashboard';
        else if (res.role === 'CANDIDATE') window.location.href = '/candidate-dashboard';
        else window.location.href = '/voter-dashboard';
    } catch (error) {
        alert('Login failed: ' + error.message);
    }
});

// === Poll Creation (Admin Dashboard) ===
document.getElementById('pollForm')?.addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = {
        pollName: document.getElementById('pollName').value.trim(),
        startDate: document.getElementById('startDate').value,
        endDate: document.getElementById('endDate').value
    };
    try {
        await apiCall('/api/admin/polls', 'POST', data);
        alert('Poll created successfully!');
        loadPolls();
    } catch (error) {
        alert('Poll creation failed: ' + error.message);
    }
});

// === Load Polls (Admin + Voter Dashboard) ===
async function loadPolls() {
    const pollContainer = document.getElementById('pollList') || document.getElementById('pollsContainer');
    if (!pollContainer) return;

    try {
        const polls = await apiCall('/api/voter/polls', 'GET');
        if (!polls.length) {
            pollContainer.innerHTML = "<p class='text-muted'>No polls available.</p>";
            return;
        }
        pollContainer.innerHTML = polls.map(p => `
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${p.pollName}</h5>
                    <p class="card-text">From ${p.startDate} to ${p.endDate}</p>
                    ${pollContainer.id === 'pollsContainer' ? 
                        `<button class="btn btn-primary" onclick="castVote(${p.id})">Vote</button>` : ''}
                </div>
            </div>
        `).join('');
    } catch (error) {
        pollContainer.innerHTML = `<p class="text-danger">Error loading polls: ${error.message}</p>`;
    }
}

// === Cast Vote (Voter Dashboard) ===
async function castVote(pollId) {
    const voterEmail = prompt("Enter your email to confirm vote:");
    const candidateName = prompt("Enter candidate name:");
    if (!voterEmail || !candidateName) return alert("Vote cancelled.");

    try {
        const res = await apiCall(`/api/voter/vote/${pollId}`, 'POST', { voterEmail, candidateName });
        alert(res);
        loadPolls();
    } catch (error) {
        alert('Voting failed: ' + error.message);
    }
}


// === Load Results (Candidate Dashboard) ===
async function loadResults() {
    const resultContainer = document.getElementById('resultsContainer');
    if (!resultContainer) return;

    try {
        const results = await apiCall('/api/candidate/results', 'GET');
        if (!results.length) {
            resultContainer.innerHTML = "<p class='text-muted'>No results available.</p>";
            return;
        }
        resultContainer.innerHTML = results.map(r => `
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${r.pollName}</h5>
                    <p class="card-text">${r.candidateName}: ${r.votes} votes</p>
                </div>
            </div>
        `).join('');
    } catch (error) {
        resultContainer.innerHTML = `<p class="text-danger">Error loading results: ${error.message}</p>`;
    }
}

// === Auto Load Dashboard Data ===
window.onload = () => {
    loadPolls();
    loadResults();
};
