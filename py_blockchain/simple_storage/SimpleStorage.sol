// SPDX-License-Identifier: MIT

pragma solidity >= 0.6.0 < 0.9.0; // specify the solidity version we are usign


contract SimpleStorage {        // class definition

    // if a variable is not inizitalize it starts with null value -> for a uint is 0

    uint256 isNumber;               // unsigned int -> initialized to 0

    function store(uint256 _isNumber) public {

        isNumber = _isNumber;

    }


    /** 
    bool isBool = false;            // bolean var
    string isString = "String";     // string
    int256 isInt = -5;              // int
    address isAddress = 0x6F5AcE12cE19b3ED8821aA91aD80Aa233393e395; // metamask public adress
    bytes32 isByte = "cat";         // byte
    */



}
