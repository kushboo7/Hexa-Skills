document.getElementById('signupForm').onsubmit = function() {
    // You can add any JavaScript code here if needed before redirection
    return true; // Ensure form submission proceeds
};

document.addEventListener('DOMContentLoaded', () => {
    const togglePassword = document.getElementById('togglePassword');
    const passwordField = document.getElementById('signupPassword');
    const togglePassword1 = document.getElementById('togglePassword1');
    const passwordField1= document.getElementById('signupPassword1');



    togglePassword.addEventListener('click', () => {
        const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordField.setAttribute('type', type);
        togglePassword.classList.toggle('fa-eye');
        togglePassword.classList.toggle('fa-eye-slash');    


    });

    togglePassword1.addEventListener('click', () => {
        const type = passwordField1.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordField1.setAttribute('type', type);
        togglePassword1.classList.toggle('fa-eye');
        togglePassword1.classList.toggle('fa-eye-slash');    


    });

    window.showLoginModal = showLoginModal;
    window.showSignupModal = showSignupModal;
    window.closeCustomAlert = closeCustomAlert;
    window.closeModals = closeModals;
});

function showCustomAlert(message) {
    const alertElement = document.getElementById('customAlert');
    const messageElement = document.getElementById('customAlertMessage');
    messageElement.textContent = message;
    alertElement.style.display = 'flex';
}

function closeCustomAlert() {
    const alertElement = document.getElementById('customAlert');
    alertElement.style.display = 'none';
}

function showLoginModal() {
    document.getElementById('loginModal').style.display = 'block';
}

function showSignupModal() {
    document.getElementById('signupModal').style.display = 'block';
}

function closeModals() {
    document.getElementById('loginModal').style.display = 'none';
    document.getElementById('signupModal').style.display = 'none';
    document.getElementById('customAlert').style.display = 'none';
}



// Separate function for OTP sending
function sendOtp() {
    let fname = document.getElementById('fname').value;
    let email = document.getElementById('email').value;
    let lname = document.getElementById('lname').value;

   

    console.log("Sending OTP...");
    Email.send({
        SecureToken: "c3277ffb-ec54-49e9-bcff-afde70489a05", // Removed extra space
        To: email,
        From: 'hariharan605203@gmail.com',
        Subject: "<<---OTP VERIFICATION HEXAWARE E-LEARNING--->>",
        Body:`
<b> Name :</b> ${fname} &nbsp; ${lname}
<br>
<b>Email :</b> ${email}
<br>
OTP : 4546
<br>

`
    }).then(
        message => {
            console.log("Email sent response:", message); // Debugging log
             alert(`OTP SENT Please Check ${email}`);
        }
    ).catch(
        error => console.error('An error occurred:', error) // Error log
    );
}

//---------------------------------newly added-----------------------------------31/08/2025(sat/6:10pm)---------//

function toggleProfileMenu() {
    const profileMenu = document.getElementById('profileMenu');
    profileMenu.style.display = profileMenu.style.display === 'block' ? 'none' : 'block';
}

// Close profile menu when clicking outside
document.addEventListener('click', function(event) {
    const isClickInside = document.querySelector('.profile-container').contains(event.target);
    if (!isClickInside) {
        document.getElementById('profileMenu').style.display = 'none';
    }
});


function viewProfile() {
    // Display the profile modal
    document.getElementById('profileModal').style.display = 'block';



    var f = localStorage.getItem("ln1");
    var e = localStorage.getItem("em1");
    var pho2 =localStorage.getItem("pho1");
    var deg2 = localStorage.getItem("deg1");
    var lin2 = localStorage.getItem("lin1");
    var git2 = localStorage.getItem("git1");
    
   
    
    
    // Simulate fetching user data (replace this with an actual data fetch in a real application)
    const userData = {
        firstName: f,
        email: e,
        degree: deg2,
        phoneNumber: pho2,
        linkedin: lin2,
        github: git2,
        // Add more fields as needed
    };
    
    // Populate the profile details in the modal
    const profileDetails = document.getElementById('profileDetails');
    profileDetails.innerHTML = `
    
        <p><strong style="color:#45b345;">Name:</strong> ${userData.firstName}</p>
        <p><strong style="color:#45b345;">Email:</strong> ${userData.email}</p>
        <p><strong style="color:#45b345;">Degree:</strong> ${userData.degree}</p>
        <p><strong style="color:#45b345;">Phone Number:</strong> ${userData.phoneNumber}</p>
        <p><strong style="color:#45b345;">LinkedIn:</strong> <a href="${userData.linkedin}" target="_blank">${userData.linkedin}</a></p>
        <p><strong style="color:#45b345;">GitHub:</strong> <a href="${userData.github}" target="_blank">${userData.github}</a></p>
        <!-- Add more fields as needed -->
    
    `;
}

// Event listener for "View Profile" link
document.querySelector('.profile-menu a[href="#"]').addEventListener('click', function(event) {
    event.preventDefault(); // Prevent the default link behavior
    viewProfile(); // Call the viewProfile function
});

function closeModals() {
    document.getElementById('loginModal').style.display = 'none';
    document.getElementById('signupModal').style.display = 'none';
    document.getElementById('profileModal').style.display = 'none';
}


 // the entire world is running for money
 function updateProfileButtonState(isActive) {
    const profileBtn = document.getElementById('.profile-menu');
    if (isActive) {
        profileBtn.classList.remove('inactive');
    } else {
        profileBtn.classList.add('inactive');
    }
}