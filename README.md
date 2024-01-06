<h1>Deep Dark Dungeon</h1>

<p>It's a simple game, inspired by the Darkest Dungeon game.</p>

<h2>Installation</h2>

<p>Just download jar file and run it.</p>
<p>Link to the installation is <a href="https://drive.google.com/file/d/143r8EWtxTJ7-jzCPyJFsUYbPk79WOdrV/view">here</a></p>

<h2>GAME</h2>
<h3>How to start???</h3>
<p>1. Choose between single and multiplayer game.</p>
<p>2. Сhoose 4 characters for whom you will play. Think over game tactics when choosing characters, as the outcome of the match depends on this. There is info button on top of each character, where is a detailed information about selected character.</p>
<p>3. If single game, choose level.</p>
<p>4. Click ==&gt;&gt; START</p>
<h3>How to play???</h3>
<p>The game takes place in turn ==&gt;&gt; First beats &gt; second beats &gt; first beats...</p>
<h4>If your turn:</h4>
<p>1. Click on atack button</p>
<p>2. Click on the enemy character, who you wanna beat.</p>
<h4>Also depending on the character you have a superpower:</h4>
<p>1. Archer - POWERSHOT ==&gt;&gt; Sniper strike, instant kill</p>
<p>2. Wizard - SUNSTRIKE ==&gt;&gt; Stabs the enemy and his neighbors on left and right.</p>
<p>3. Paladin - HEAL ==&gt;&gt; Heals the weakest character.</p>
<p>4. Warrior - BERSERK CALL ==&gt;&gt; The next blow of the opponent will be on this character.</p>
<p>If single game and you win, the next lvl will be unlocked.</p>

<img src="https://media.discordapp.net/attachments/695300436319535174/712687994309443624/unknown.png" alt="Architecture">

<h2>Built With</h2>

<p>Used LibGdx framework</p>
<p>Created on:</p>
<ul>
<li><p>Java</p></li>
<li><p>HTML</p></li>
<li><p>CSS</p></li>
<li><p>Javascript</p></li>
</ul>

<h2>Textures</h2>
<p>Most of textures were made in <a href="http://pixelartmaker.com">Pixel Art Maker</a>, special thanks to <a href="https://www.linkedin.com/in/davideyork/">David York</a> for characters sprites.</p>

<h2>AI</h2>
<p>Our AI is interesting and at the same time smart, at his turn he analyzes who is the most strongest enemy and strikes him
We have such scheme healer> magic>archer>warrior
Healer is the most strongest in our oppinion, because he has heal and he can rewive someone, so the hero will come back in the fight. Next one is magic he has spell "suntrike" it deals 30 damage to a hero and 2 nearby enemys, so total of 90 damage, but to different heros, next one is archer he has very powerfull spell, but it costs alot, next one is warror he has alot of health and medium damage, so there is no point to attack him forst, but he can steal your attack by using his spell agro.
But our AI never follows this scheme healer > magic>archer>warrior once in 2 turns it attack random unit, or if someone has lower than 30 health it attacks him, to finish him, so he cant attack any more
</p>

<h2>Clien-Server</h2>
<p>When connecting to the server, the client sends a list with the names of the characters he is fighting for. The server saves the client list and waits for another client. After the other client connects, the game starts and the server sends the opponent's characters to each player. Server also tells who goes first. The one whose move makes the move and after making the move sends an infopacket: which character hit, which character was hit and with what stroke. The client who waits for his move, always sends a packet with a request to see if he can now hit. As long as the opponent doesn’t send the infopacket, he won't get anything. If the opponent has made his turn and sent information about the move, he will receive this information in response and now he will go and the opponent will ask the server about the move all the time.
</p>

<h2>Team</h2>

<p>Artur-Aleksanrd Pärnoja</p>
<p>Ilja Boitšuk</p>
<p>Nikita Birjukovs</p>

<h4>Speccial thanks to:</h4>
<p>Anna Grund</p>
<p>Janar Keit Jaakson</p>
