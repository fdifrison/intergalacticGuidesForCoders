// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0; // specify the solidity version we are usign


contract SimpleStorage {        // class definition

    // if a variable is not inizitalize it starts with null value -> for a uint is 0
    // if the visibility is not specified it is automatically set to internal
    uint256 isNumber;               // unsigned int -> initialized to 0

    // public function can be called by both internal and external variable
    
    function store(uint256 _isNumber) public {
        isNumber = _isNumber;
    }
    
    function retrieve() public view returns(uint256)  {
        return isNumber;
    }

}

    /** 
    bool isBool = false;            // bolean var
    string isString = "String";     // string
    int256 isInt = -5;              // int
    address isAddress = 0x6F5AcE12cE19b3ED8821aA91aD80Aa233393e395; // metamask public adress
    bytes32 isByte = "cat";         // byte
    */

