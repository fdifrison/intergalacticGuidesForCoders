# Blockchain 101
A blockchain is essentially a decentralized database

### `Consensus, Sibyl resistances`
It refers to the defensive mechanism the blockchains have to avoid a hacker's attack that aims to take control over the chain consensus. It takes different forms depending on the blockchain consensus type:
* `Nakamoto consensus`: is composed of two-part:
    * `Proof-of-Work`: the difficulty of mining (the computational resources needed) is the first Sibyl resistant mechanism. Who mine a block became the author of it. There is no use in creating fake accounts to attack the chain since the mining complexity remains the same.
    The miners are the ones that compete for the gas fees paid by the user of the blockchain to perform actions (In addition to the block reward (token), obtained to "solve the block challenge")
        * Pro & Con : it is very energy demanding to mine a node, bt very decentralized
    * Longest chain (`chain selection`): the number of blocks is used to recognize the true blockchain. The longest is the legit chain. For a hacker attack, the same author has to own the majority of the blocks in the chain.

* `Proof-of-Stake`: there are no more miners but validators that guarantee the safety of the network staking a certain amount of token (32 eth at the moment). If the validators behave improperly, they risk to loose part of their stake (i.e. a lot of money), and due to the decentralized infrastructure of the chain is fairly easy to verify if a validator is following the rules.
    * Pro & Con : the energy demand problem is resolved since only one nodes (randomly chosen) operates to solve the new block, while the validators only need to validate. As a Con, PoS are considered to be less decentralized due to the staking cost not accessible to everyone (atm 32 eth are nearly 130k $).

### `Sharding`
One of the main problem of today blockchain is scalability, since gas fees increase as the number of user increase (so the execution time). Sharding is a new paradigm that eth2 will introduce: basically there will be a main blockchain and a series of secondary bc that hook in to the main one. this will increase exponentially the `block space`, i.e. the number of transaction that can be performed 

### `Rollup`
Are layer 2 blockchain (similar to shards) that rely on the layer 1 for security and transactions. Solve some of the scalability issues. 

### `Side-chains`
Are layer 2 blockchain that don't rely on the main chain for security but implement their own rules

### `Gas fees`

transaction fee to be paid in mining base blockchain (proof of stake)

https://legacy.ethgasstation.info/blog/eip-1559/ new gas fee protocol: fees for transactions are divided into base fees (that will be burned) and tip fees

https://ethgasstation.info/ gives updated values on gas fees




# Utilities

### `Metamask`

is a wallet based on ethereum, useful to make fake transactions and test smart contracts in the development phase

### `Etherscan` -> `https://etherscan.io/`

is a platform (Block Explorer) to search for transactions and wallets information

### `Testnet`

is a blockchain made for testing purposes

### `Faucet`

are services that provide fake crypto to be used on testnets

* <https://faucet.rinkeby.io/> -> works only with Twitter



# Developer stuff

## `Remix`
Is the starting point for solidity developer; an IDE useful to learn how to write and deploy smart contract with the aid of metamask. You can deploy locally (Js VM) or on a real blockchain.

