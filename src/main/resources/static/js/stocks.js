// Attends qu'Alpine soit prêt avant de définir stocksData
document.addEventListener('alpine:init', () => {
    console.log('✅ Alpine ready, initializing stocksData...');

    window.stocksData = function() {
        return {
            stocks: [],
            loading: true,
            error: null,
            creating: false,
            newStock: {
                symbol: '',
                name: '',
                price: 0
            },

            init() {
                console.log('📊 Initializing stocks page...');
                this.loadStocks();
            },

            async loadStocks() {
                try {
                    this.loading = true;
                    this.error = null;
                    console.log('🔄 Loading stocks...');

                    this.stocks = await API.get('/stocks');

                    console.log('✅ Stocks loaded:', this.stocks);
                } catch (e) {
                    console.error('❌ Error loading stocks:', e);
                    this.error = 'Erreur lors du chargement des actions : ' + e.message;
                } finally {
                    this.loading = false;
                }
            },

            async createStock() {
                // Validation
                if (!this.newStock.symbol || !this.newStock.name || !this.newStock.price) {
                    alert('Veuillez remplir tous les champs');
                    return;
                }

                if (this.newStock.price <= 0) {
                    alert('Le prix doit être supérieur à 0');
                    return;
                }

                try {
                    this.creating = true;
                    console.log('➕ Creating stock:', this.newStock);

                    const newStock = await API.post('/stocks', {
                        symbol: this.newStock.symbol.toUpperCase(), // Force majuscules
                        name: this.newStock.name,
                        price: parseFloat(this.newStock.price)
                    });

                    console.log('✅ Stock created:', newStock);

                    // Ajoute la nouvelle action à la liste
                    this.stocks.push(newStock);

                    // Réinitialise le formulaire
                    this.newStock = { symbol: '', name: '', price: 0 };

                    // Message de succès
                    alert('✅ Action ajoutée avec succès !');

                } catch (e) {
                    console.error('❌ Error creating stock:', e);

                    // Message d'erreur plus explicite
                    if (e.message.includes('409') || e.message.includes('existe déjà')) {
                        alert('❌ Ce symbole boursier existe déjà !');
                    } else {
                        alert('❌ Erreur lors de l\'ajout : ' + e.message);
                    }
                } finally {
                    this.creating = false;
                }
            },

            /**
             * Formate le prix avec symbole €
             */
            formatPrice(price) {
                return new Intl.NumberFormat('fr-FR', {
                    style: 'currency',
                    currency: 'EUR',
                    minimumFractionDigits: 2
                }).format(price);
            },

            /**
             * Formate la date de dernière mise à jour
             */
            formatDate(timestamp) {
                const date = new Date(timestamp);
                return new Intl.DateTimeFormat('fr-FR', {
                    day: '2-digit',
                    month: '2-digit',
                    year: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit'
                }).format(date);
            }
        }
    };
});

// API Helper (utilise le même que app.js)
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