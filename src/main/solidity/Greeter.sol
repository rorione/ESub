// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract Greeter {
    address owner;
    string greeting;

    constructor(string memory _greeting) {
        owner = msg.sender;
        greeting = _greeting;
    }

    function getGreeting() external view returns (string memory)  {
        return greeting;
    }

    function kill() public {
        if (msg.sender == owner) selfdestruct(payable(owner));
    }
}
