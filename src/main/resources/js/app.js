// Asigna eventos al cargar la página
document.addEventListener("DOMContentLoaded", function () {
    // document.getElementById("btnPedido").addEventListener("click", showForm);
    document.getElementById("btnSubmit").addEventListener("click", loadPostOptions);
});
let userName = null;

function loadPostOptions() {
    const nameValue = document.getElementById("nameInput").value.trim();
    if (!nameValue) {
        alert("Por favor ingresa tu nombre");
        return;
    }

    const nameEncoded = encodeURIComponent(nameValue);

    fetch(`/home?name=${nameEncoded}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name: nameValue }),

    })
        .then(response => response.json())
        .then(data => {
            console.log("Response from server:", data);
            userName = data.name;
            getOptions(userName);
        })
        .catch(err => console.error("Error al cargar las opciones:", err));
}
function getOptions(data) {
    console.log("Data received:", data);
    fetch("http://localhost:35000/layouts/options.html")
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }
            return response.text();
        })
        .then(html => {
            document.body.innerHTML = html;
            if (data) {
                const section = document.getElementById("continer");
                if (section) {
                    const greeting = document.createElement("p");
                    greeting.id = "greeting";
                    greeting.textContent = `Welcome ${data}!`;
                    section.insertBefore(greeting, section.firstChild);
                }
            }
        })
        .catch(err => {
            console.error("No se pudo cargar las opciones:", err);
        });
}

// function getMenu() {
//     fetch("http://localhost:35000/layouts/menu.html")
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error(`Error HTTP: ${response.status}`);
//             }
//             return response.text();
//         })
//         .then(html => {
//             document.body.innerHTML = html;
//         })
//         .catch(err => {
//             console.error("No se pudo cargar el menú:", err);
//         });
// }
    function getMenu() {
        fetch("http://localhost:35000/layouts/menu.html")
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error HTTP: ${response.status}`);
                }
                return response.text();
            })
            
            .then(html => {
                document.body.innerHTML = html;
            })
            .catch(err => {
                console.error("No se pudo cargar el menú:", err);
            });
        fetch("http://localhost:35000/menu")
            .then(data => data.json())
            .then(dishes => {
                console.log("Menu JSON received:", dishes);
             // Limpiar opciones existentes
            document.getElementById("burger options").innerHTML = "";
            document.getElementById("pizza options").innerHTML = "";
            document.getElementById("hotdog options").innerHTML = "";

            dishes.forEach(dish => {
                let container;
                if (dish.category.toLowerCase() === "burger") {
                    container = document.getElementById("burger options");
                } else if (dish.category.toLowerCase() === "pizza") {
                    container = document.getElementById("pizza options");
                } else if (dish.category.toLowerCase() === "hotdog") {
                    container = document.getElementById("hotdog options");
                } else {
                    console.warn("Categoría no reconocida:", dish);
                    return;
                }

                const plateDiv = document.createElement("div");
                plateDiv.className = "plate";
                plateDiv.innerHTML = `
                    <p>${dish.name}</p>
                    <p>---------------------</p>
                    <p>$${dish.price}</p>
                `;

                container.appendChild(plateDiv);
                console.log(`Added ${dish.name} to ${dish.category}`);
            });
        })
        .catch(err => {
            console.error("No se pudo cargar el menú:", err);
        });
    }
// function getMenu() {
//     console.log("Loading menu HTML...");
//     fetch("http://localhost:35000/layouts/menu.html")
//         .then(response => {
//             if (!response.ok) throw new Error(`HTTP error ${response.status}`);
//             return response.text();
//         })
//         .then(html => {
//             document.getElementById("main-content").innerHTML = html; // o document.body.innerHTML si quieres reemplazar todo
//             console.log("Menu HTML loaded, now fetching dishes...");

//             // ahora hacemos fetch de los platos
//             return fetch("http://localhost:35000/menu");
//         })
//         .then(response => {
//             if (!response.ok) throw new Error(`HTTP error ${response.status}`);
//             return response.json();
//         })
//         .then(dishes => {
//             console.log("Menu JSON received:", dishes);

//             const categories = ["Burger", "Pizza", "Hot Dog"];
//             categories.forEach(category => {
//                 const categorySection = Array.from(document.querySelectorAll("h1"))
//                     .find(h1 => h1.textContent === category);
//                 if (!categorySection) return;

//                 const optionsList = categorySection.nextElementSibling;
//                 if (!optionsList) return;

//                 optionsList.innerHTML = ""; // limpiamos plates existentes

//                 const dishesOfCategory = dishes.filter(d => d.category === category);
//                 dishesOfCategory.forEach(dish => {
//                     const plateDiv = document.createElement("div");
//                     plateDiv.className = "plate";
//                     plateDiv.innerHTML = `
//                         <p>${dish.name}</p>
//                         <p>---------------------</p>
//                         <p>$${dish.price}</p>
//                     `;
//                     optionsList.appendChild(plateDiv);
//                 });
//                 console.log(`Category ${category} updated with ${dishesOfCategory.length} dishes`);
//             });
//         })
//         .catch(err => console.error("Error loading menu:", err));
// }


function getHome() {
    getOptions(userName);
}
function showForm() {
    fetch("http://localhost:35000/layouts/order.html")
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }
            return response.text();
        })
        .then(html => {
            document.body.innerHTML = html;
            Order();
        })
        .catch(err => {
            console.error("No se pudo cargar el formulario:", err);
        });
}

function Order() {
    const form = document.getElementById("orderForm");
    if (!form) {
        console.error("No se encontró el formulario");
        return;
    }

    form.addEventListener("submit", function (event) {
        event.preventDefault();
        alert("Order submitted! Thank you.");
        getHome();
    });
}
