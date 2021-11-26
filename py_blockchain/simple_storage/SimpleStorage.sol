// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0; // specify the solidity version we are usign


contract SimpleStorage {        // class definition

    // if a variable is not inizitalize it starts with null value -> for a uint is 0
    // if the visibility is not specified it is automatically set to internal
    uint256 age;               // unsigned int -> initialized to 0

    // public function can be called by both internal and external variable

    struct People {
        uint256 age; //index 0
        string name; //index 1
    }

    People[] public people; // dynamic array of name people
    // People[1] public people; // fix-size array
    // People public person = People({age:2, name:"Yuri"}); statically populate the array

    mapping(string => uint256) public nameToAge;
    
    function store(uint256 _age) public {
        age = _age;
    }
    
    function retrieve() public view returns(uint256)  {
        return age;
    }

    function addPerson(uint256 _age, string memory _name) public {
        people.push(People(_age, _name)); // store people object in array -> the order has to be the same of the struct People obj
        nameToAge[_name] = _age; // map name to age
    }

}

    /** 
    bool isBool = false;            // bolean var
    string isString = "String";     // string
    int256 isInt = -5;              // int
    address isAddress = 0x6F5AcE12cE19b3ED8821aA91aD80Aa233393e395; // metamask public adress
    bytes32 isByte = "cat";         // byte
    */

