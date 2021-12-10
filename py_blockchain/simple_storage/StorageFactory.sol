// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0; // specify the solidity version we are usign


// import the SimpleStorage contract. It is the same as revrite the whole contract here
import "./SimpleStorage.sol"; 

// we want to create a contract that allow the user to deploy SimpleStorage contract
contract StorageFactory {

    SimpleStorage[] public contractStorageArray; //create a container of the newly created contracts

    function createSimpleStorageContract() public {
        //create an object of type SimpleStorage with the name simpleStorage
        //assign to the name simplestorage a new contract Simplestorage() that takes no arguments
        SimpleStorage simpleStorage = new SimpleStorage(); 
        contractStorageArray.push(simpleStorage); // insert in the 
    }

    // store data in the newly created simplestorage instance given its index
    // e. sfStore 0 , 55 -> store 55 in the simplestorage of index 0 in the StorageFactory array (SimpleStorage)
    function sfStore(uint256 _contractStorageIndex, uint256 _contractStorageNumber) public {
        // Address
        // ABI Application Binary Interface
        SimpleStorage simpleStorage = SimpleStorage(address(contractStorageArray[_contractStorageIndex]));
        simpleStorage.store(_contractStorageNumber);
    }
}