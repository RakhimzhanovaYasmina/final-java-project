let products = [];

let cart = [];

async function loadProducts() {

    const response =
        await fetch('/products');

    products =
        await response.json();

    displayProducts(products);
}

function displayProducts(productsList) {

    const container =
        document.getElementById('productsContainer');

    container.innerHTML = '';

    if (productsList.length === 0) {

        container.innerHTML = `

            <h2 style="
                grid-column:1/-1;
                text-align:center;
                color:#777;
            ">
                No products found
            </h2>

        `;

        return;
    }

    productsList.forEach(product => {

        let image =
            "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?q=80&w=800";

        if (
            product.title &&
            product.title.toLowerCase().includes('hoodie')
        ) {

            image =
                "https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?q=80&w=800";
        }

        if (
            product.title &&
            product.title.toLowerCase().includes('jacket')
        ) {

            image =
                "https://images.unsplash.com/photo-1523398002811-999ca8dec234?q=80&w=800";
        }

        if (
            product.title &&
            product.title.toLowerCase().includes('dress')
        ) {

            image =
                "https://images.unsplash.com/photo-1496747611176-843222e1e57c?q=80&w=800";
        }

        container.innerHTML += `

        <div class="product-card">

            <img
                src="${image}"
                class="product-image"
                alt="${product.title}"
            >

            <div class="product-info">

                <h3 class="product-title">
                    ${product.title}
                </h3>

                <p class="product-description">
                    ${product.description}
                </p>

                <div class="product-price">
                    $${product.price}
                </div>

                <div class="product-buttons">

                    <button
                        class="add-btn"
                        onclick="addToCart(${product.id})">

                        Add to Cart

                    </button>

                </div>

            </div>

        </div>

        `;
    });
}

function addToCart(productId) {

    const product =
        products.find(p => p.id === productId);

    if (!product) return;

    cart.push(product);

    updateCart();
}

function updateCart() {

    const cartItems =
        document.getElementById('cartItems');

    const cartTotal =
        document.getElementById('cartTotal');

    cartItems.innerHTML = '';

    let total = 0;

    if (cart.length === 0) {

        cartItems.innerHTML = `

            <p style="color:#777;">
                Cart is empty
            </p>

        `;
    }

    cart.forEach((item, index) => {

        total += Number(item.price);

        cartItems.innerHTML += `

        <div class="cart-item">

            <strong>
                ${item.title}
            </strong>

            <p style="margin-top:6px;">
                $${item.price}
            </p>

            <button
                class="remove-btn"
                onclick="removeFromCart(${index})">

                Remove

            </button>

        </div>

        `;
    });

    cartTotal.innerHTML =
        `Total: $${total.toFixed(2)}`;
}

function removeFromCart(index) {

    cart.splice(index, 1);

    updateCart();
}

async function checkout() {

    const customerName =
        document.getElementById('customerName').value;

    if (customerName.trim() === '') {

        alert('Please enter your name');

        return;
    }

    if (cart.length === 0) {

        alert('Cart is empty');

        return;
    }

    let total = 0;

    cart.forEach(item => {

        total += Number(item.price);
    });

    const order = {

        customerName: customerName,

        totalPrice: total,

        status: "NEW",

        products: cart
    };

    const response = await fetch('/orders', {

        method: 'POST',

        headers: {
            'Content-Type': 'application/json'
        },

        body: JSON.stringify(order)
    });

    if (response.ok) {

        alert('Order created successfully');

        cart = [];

        updateCart();

        document.getElementById('customerName').value = '';
    }

    else {

        alert('Checkout failed');
    }
}

async function createProduct() {

    const title =
        document.getElementById('newTitle').value;

    const description =
        document.getElementById('newDescription').value;

    const price =
        document.getElementById('newPrice').value;

    if (
        title.trim() === '' ||
        description.trim() === '' ||
        price.trim() === ''
    ) {

        alert('Fill all fields');

        return;
    }

    const newProduct = {

        title: title,

        description: description,

        price: Number(price)
    };

    const response = await fetch('/products', {

        method: 'POST',

        headers: {
            'Content-Type': 'application/json'
        },

        body: JSON.stringify(newProduct)
    });

    if (response.ok) {

        alert('Product added successfully');

        document.getElementById('newTitle').value = '';

        document.getElementById('newDescription').value = '';

        document.getElementById('newPrice').value = '';

        loadProducts();
    }

    else {

        alert('Create product failed');
    }
}

async function deleteAdminProduct() {

    const id =
        document.getElementById('deleteId').value;

    if (id.trim() === '') {

        alert('Enter product ID');

        return;
    }

    const response = await fetch(`/products/${id}`, {

        method: 'DELETE'
    });

    if (response.ok) {

        alert('Product deleted');

        document.getElementById('deleteId').value = '';

        loadProducts();
    }

    else {

        alert('Delete failed');
    }
}

document
    .getElementById('searchInput')

    .addEventListener('input', function () {

        const value =
            this.value.trim().toLowerCase();

        if (value === '') {

            displayProducts(products);

            return;
        }

        const filteredProducts =

            products.filter(product =>

                product.title &&
                product.title
                    .toLowerCase()
                    .includes(value)
            );

        displayProducts(filteredProducts);
    });

loadProducts();

updateCart();

async function updateProduct() {

    const id =
        document.getElementById('updateId').value;

    const title =
        document.getElementById('updateTitle').value;

    const description =
        document.getElementById('updateDescription').value;

    const price =
        document.getElementById('updatePrice').value;

    if (
        id.trim() === '' ||
        title.trim() === '' ||
        description.trim() === '' ||
        price.trim() === ''
    ) {

        alert('Fill all update fields');

        return;
    }

    const updatedProduct = {

        title: title,

        description: description,

        price: Number(price)
    };

    const response = await fetch(`/products/${id}`, {

        method: 'PUT',

        headers: {
            'Content-Type': 'application/json'
        },

        body: JSON.stringify(updatedProduct)
    });

    if (response.ok) {

        alert('Product updated successfully');

        document.getElementById('updateId').value = '';

        document.getElementById('updateTitle').value = '';

        document.getElementById('updateDescription').value = '';

        document.getElementById('updatePrice').value = '';

        loadProducts();
    }

    else {

        alert('Update failed');
    }
}