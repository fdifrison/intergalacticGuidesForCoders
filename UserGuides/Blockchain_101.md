<h1>Blockchain 101</h1>

A blockchain is essentially a decentralized database

TOC

- [`Definitions`](#definitions)
  - [`Consensus: Sibyl resistances`](#consensus-sibyl-resistances)
  - [`Sharding`](#sharding)
  - [`Rollup`](#rollup)
  - [`Side-chains`](#side-chains)
  - [`Gas-fees`](#gas-fees)
  - [`EVM Ethereum Virtual Machine`](#evm-ethereum-virtual-machine)
- [Utilities](#utilities)
  - [`Metamask`](#metamask)
  - [`Etherscan` -> `https://etherscan.io/`](#etherscan---httpsetherscanio)
  - [`Testnet`](#testnet)
  - [`Faucet`](#faucet)
- [Developer stuff](#developer-stuff)
  - [`Remix`](#remix)
- [Solidity](#solidity)
  - [`Initialization`](#initialization)
  - [`Contracts`](#contracts)
  - [`Functions`](#functions)
  - [`Struct`](#struct)
  - [`Array`](#array)
  - [`Mapping`](#mapping)
  - [`Visibility`](#visibility)
  - [`View and Pure Function`](#view-and-pure-function)
  - [`Global and Function scope`](#global-and-function-scope)
  - [`Storing order`](#storing-order)

# `Definitions`

## `Consensus: Sibyl resistances`

It refers to the defensive mechanism the blockchains have to avoid a hacker's attack that aims to take control over the chain consensus. It takes different forms depending on the blockchain consensus type:

- `Nakamoto-consensus`: is composed of two-part:
  - `Proof-of-Work`: the difficulty of mining (the computational resources needed) is the first Sibyl resistant mechanism. Who mine a block became the author of it. There is no use in creating fake accounts to attack the chain since the mining complexity remains the same.
    The miners are the ones that compete for the gas fees paid by the user of the blockchain to perform actions (In addition to the block reward (token), obtained to "solve the block challenge")
    - Pro & Con: it is very energy demanding to mine a node, but very decentralized
  - Longest chain (`chain selection`): the number of blocks is used to recognize the true blockchain. The longest is the legit chain. For a hacker attack, the same author has to own the majority of the blocks in the chain.

- `Proof-of-Stake`: there are no more miners but validators that guarantee the safety of the network staking a certain amount of token (32 eth at the moment). If the validators behave improperly, they risk to lose part of their stake (i.e. a lot of money), and due to the decentralized infrastructure of the chain is fairly easy to verify if a validator is following the rules.
  - Pro & Con: the energy demand problem is resolved since only one node (randomly chosen) operates to solve the new block, while the validators only need to validate. As a Con, PoS is considered to be less decentralized due to the staking cost not being accessible to everyone (atm 32 eth are nearly 130k $).

## `Sharding`

One of the main problems of today's blockchain is scalability since gas fees increase as the number of user increase (so the execution time). Sharding is a new paradigm that eth2 will introduce: there will be the main blockchain and a series of secondary bc that hook into the main one. this will increase exponentially the `block space`, i.e. the number of transactions that can be performed

## `Rollup`

Are layer 2 blockchain (similar to shards) that rely on layer 1 for security and transactions. Solve some of the scalability issues.

## `Side-chains`

Are layer 2 blockchain that don't rely on the main chain for security but implement their own rules

## `Gas-fees`

transaction fee to be paid in mining base blockchain (proof of stake)

<https://legacy.ethgasstation.info/blog/eip-1559/> new gas fee protocol: fees for transactions are divided into base fees (that will be burned) and tip fees

<https://ethgasstation.info/> gives updated values on gas fees

## `EVM Ethereum Virtual Machine`

Many blockchain out there are so called "evm compatible" meaning that their smart contract can be written in solidity as if they were deployed on the ethereum blockchain.

# Utilities

## `Metamask`

is a wallet based on ethereum, useful to make fake transactions and test smart contracts in the development phase

## `Etherscan` -> `https://etherscan.io/`

is a platform (Block Explorer) to search for transactions and wallets information

## `Testnet`

is a blockchain made for testing purposes

## `Faucet`

are services that provide fake crypto to be used on testnets

- <https://faucet.rinkeby.io/> -> works only with Twitter

# Developer stuff

## `Remix`

Is the starting point for solidity developer; an IDE useful to learn how to write and deploy smart contract with the aid of metamask. You can deploy locally with a `Javascript VM` or on a blockchain (real or testnet) switching to `Injected Web3`.

# Solidity

Is the programming language that interacts with the ethereum blockchain.

## `Initialization`

Every smart contract have to start with the following statement:

    pragma solidity >= 0.6.0 < 0.9.0;
where the version of solidity is stated (from 0.6.0 to 0.9.0 in this case). when using `^0.6.0` it means that the accepted solidity version are from 0.6.0 above.

## `Contracts`

The smart contract body is contained inside the class definition:

    contract Name { }

## `Functions`

Functions are defined as follow:

    function func_name(type var_name) public {
        my_var = var_name;
    }

where `type` is the variable type (es. uint256) and `public` is the `visibility attribute`

Function can store variable in two ways:

- `memory`: variable will be stored only during the execution of the function
- `storage`: data will persist even after the function is executed

strings in solidity are not a type but an array of bytes (object) where we can append a strings.

es.

    function addPerson(uint256 _isNumber, string memory _name) public {
        people.push(People(_isNumber, _name));
    }

## `Struct`

Is a method that allows creating a new type objects (sounds like a class in python)

    struct People {
        uint256 age;
        string name;
    }
We are creating a type named `People` that has two attributes, `age` and `name`. We can call the new type as follow:

    People public person = People({age: 2, name: "Giovanni"})
where `person` is the name of the People instance.

## `Array`

We can have dynamic or fixed size arrays:

```
People[] public people; // dynamic array
People[1] public people; // one element fix-size array
```

we can populate the array as follow:

    People public person = People({age:2, name:"Yuri"});

where in the rhs `People` is a `struct` object previously defined

## `Mapping`

is a  type of data structure that serves as a dictionary. It can then be connected to a function that store data and use it as a retriever.

```
mapping(string => uint256) public nameToAge;

function addPerson(uint256 _age, string memory _name) public {
    people.push(People(_age, _name)); // store people object in array
    nameToAge[_name] = _age; // map name to age
}
```

## `Visibility`

Depending on the use of a function/variable we can set its visibility constraint. If nothing is specified that the `default visibility` is set to `internal`.

function and variable can be:

- `internal`: can be used only in the current contract or in the ones deriving from it;
- `external`: can be called from other contracts and transactions but cannot be called internally;
- `private`: are only visible in the current contract;
- `public`: are part of the contract interface so can be called both internally and externally.

## `View and Pure Function`

Are function that does not require a transaction on the blockchain (i.e. no gas fees are required).
View function are used to display something to the contract interface while pure function are used to do simple arithmetics operations.

    function retrieve() public view returns(var_type)  {
            var;
            }

    function retrieve() public pure { 
            var + var;
            }

N.b a public variable (es. `uint256 public`) are a type of view function, meaning that their value can be inspected from the contract interface without requiring a blockchain transaction.

## `Global and Function scope`

Global scope is the space inside the contract parenthesis {}. If a variable is defined in the global scope it is callable by the functions inside the global scope. Instead, if a variable is defined in the function scope (i.e. the body of the function), then it won't be accessible from outside the function.

## `Storing order`
