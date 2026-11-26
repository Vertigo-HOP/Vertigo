<script>
    let username = '';
    let password = '';

    function handleLogin() {
        console.log('Email:', username );
        console.log('Password:', password);
    }
</script>

<div class="login-container">
    <div>
    <img src="../public/White_Logo_Big_Top.png" alt="vertigo logo" />
        <div><!--<h1>VERTIGO</h1>-->
    <h5>lagerstyring</h5>
    </div></div>

    <div>

        <h4>VELKOMMEN TILBAGE</h4>
        <h2> Log ind på lageret </h2>
        <h3>Hold styr på udstyr og pakkelister</h3>

        <form onsubmit={(event) => {
            event.preventDefault();
            handleLogin();
        }}>
            <input
                type="text"
                placeholder="Email"
                bind:value={username}
                autofocus
            />

            <input
                type="password"
                placeholder="Password"
                bind:value={password}
            />

            <button type="submit">Login</button>

        </form>
    </div>
</div>


<style>
    .login-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
    }

    form {
        display: flex;
        flex-direction: column;
        gap: 15px;
        width: 280px;
    }

    input {
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }

    button {
        padding: 12px;
        background-color: #9DA9FF;
        color: #0A0E1C;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }

    button:hover {
        background-color: #B2BBFF;
    }

    h4 {
        color: #9DA9FF;
        font-size: small;
    }

    h3 {
        color: #656A7C;
        font-size: small;
    }


</style>