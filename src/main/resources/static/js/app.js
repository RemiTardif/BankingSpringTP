// Attends qu'Alpine soit prêt avant de définir accountsData
document.addEventListener('alpine:init', () => {
    console.log('✅ Alpine ready, initializing accountsData...');

    window.accountsData = function() {
        return {
            accounts: [],
            loading: true,
            error: null,
            creating: false,
            clientId: null,
            newAccount: { name: '', type: '', solde: 0 },

            init() {
                const url = window.location.pathname;
                this.clientId = url.split('/').pop();
                console.log('📍 Client ID:', this.clientId);
                this.loadAccounts();
            },

            async loadAccounts() {
                try {
                    this.loading = true;
                    this.error = null;
                    console.log('🔄 Loading accounts...');
                    this.accounts = await API.get(`/accounts/client/${this.clientId}`);
                    console.log('✅ Accounts loaded:', this.accounts);
                } catch (e) {
                    console.error('❌ Error:', e);
                    this.error = 'Erreur: ' + e.message;
                } finally {
                    this.loading = false;
                }
            },

            async createAccount() {
                if (!this.newAccount.name || !this.newAccount.type) {
                    alert('Veuillez remplir tous les champs');
                    return;
                }

                try {
                    this.creating = true;
                    const newAccount = await API.post('/accounts', {
                        clientId: this.clientId,
                        name: this.newAccount.name,
                        type: this.newAccount.type,
                        solde: parseFloat(this.newAccount.solde) || 0
                    });

                    this.accounts.push(newAccount);
                    this.newAccount = { name: '', type: '', solde: 0 };
                    console.log('✅ Account created!');
                } catch (e) {
                    console.error('❌ Error:', e);
                    alert('Erreur: ' + e.message);
                } finally {
                    this.creating = false;
                }
            },

            formatType(type) {
                const types = {
                    'CHECKING': 'Compte Courant',
                    'SAVINGS': 'Livret A',
                    'PEA': 'PEA',
                    'BUSINESS': 'Compte Professionnel'
                };
                return types[type] || type;
            },

            formatCurrency(amount) {
                return new Intl.NumberFormat('fr-FR', {
                    style: 'currency',
                    currency: 'EUR'
                }).format(amount);
            }
        }
    };
});

// API Helper
const API = {
    BASE_URL: '/api',

    async get(endpoint) {
        const response = await fetch(`${this.BASE_URL}${endpoint}`);
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        return response.json();
    },

    async post(endpoint, data) {
        const response = await fetch(`${this.BASE_URL}${endpoint}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        if (!response.ok) {
            const err = await response.json();
            throw new Error(err.message || `HTTP ${response.status}`);
        }
        return response.json();
    }
};