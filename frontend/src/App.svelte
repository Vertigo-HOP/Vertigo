<!-- <script>

  import { onMount } from 'svelte';

  let equipment = [];
  let error = "";

  onMount(async () => {
    try {
      const response = await fetch(
              'http://localhost:8080/api/equipment'
      );

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


-->

<script>
  import Login from './pages/Login.svelte';
</script>

<Login />