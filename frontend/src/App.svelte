<script>

  import { onMount } from 'svelte';

  const apiUrl = import.meta.env.VITE_API_URL;
  let equipment = [];
  let error = "";


  onMount(async () => {
    try {
      console.log(apiUrl)
      const response = await fetch(
              `${apiUrl}/api/equipment/equipments`
      );

      console.log(apiUrl)
      if(!response.ok) {
        throw new Error('could not get equipment');
      }

      equipment = await response.json();
    } catch (err) {
      error = err.message;
    }
  });
</script>


<h1>Equipment</h1>

{#if error}
  <p>{error}</p>

{:else}
  {#each equipment as item}
    <p>{item.name}</p>
  {/each}

{/if}



<!--
<script>
  import Login from './pages/Login.svelte';
</script>

<Login />
-->