# AWS Cloud practitioner exam CLF-C01

[AWS-Cost-Calculator](https://calculator.aws/#/)

[Resources for AWS Certifications](https://aws.amazon.com/it/certification/certified-cloud-practitioner/?c=sec&sec=resources>)

[Free course with tests from aCloud.Guru](https://learn.acloud.guru/course/aws--certified-cloud-practitioner/overview)

**Cloud computing** is the **on-demand delivery** of computer power, database storage, applications and other IT
services that is delivered through a **cloud service** (i.e. AWS or GCP) with **pay-as-you-go** pricing (you pay when
you consume). Scaling (up or down) is also on-demand and almost instantaneous. The service provider takes care of
maintenance, you only use what you need!

We can have:

* Private Cloud like **rackspace** (out-of-scope)
* **Public Cloud** (AWS, GSP, AZ), which are 3rd parties cloud resources delivered over the internet
* **Hybrid Cloud**, where some servers are private and some capabilities are on public cloud; it gives the ability to
  control sensitive assets on private cloud and still leverage flexibility and effectiveness of public cloud services

The `five` **Keys** of Cloud computing are:

* **On-demand self-service** without human interaction with the provider
* **Broad network access** from anywhere
* **Multi-tenancy** : multiple customers can share the same infrastructure without renouncing to security and privacy
* **Elasticity and scalability** on-demand
* **Measured server**: you pay for what you use

The `Six` **Advantages** of Cloud computing are:

* **Trade capital expense (CAPEX) for operational expense (OPEX)**: pay on-demand not the hardware, reduce **TCO** (
  Total Cost of Ownership) and **OPEX** (Operational Expense)
* **Economy of scale**: price are reduced as the increase of efficiency
* **Stop Guessing capacity**: we will scale on actual usage metrics
* **Increase speed and agility**
* **No costs of maintenance**
* **Up and running in minutes**

# Types of Cloud Computing

* `IaaS` **Infrastructure as a Service**: first step in the transition from traditional IT service; provides networking,
  computers, data storage with a **high level of flexibility**
* `PaaS` **Platform as a Service**: no need to take care of the underlying infrastructure; focus on deployment and
  managing of your application
* `FaaS` **Function as a Service**: Lambda function
* `SaaS` **Software as a Service**: we only use the product that is run and managed from the provider (the lowest level
  of flexibility)

<img src="./Images/CCTypes.PNG" alt="Not Found">

# AWS specific

## How to access AWS

We have 3 ways to access AWS services:

* AWS Management Console, protected by password and MFA
* AWS CLI protected by access key (open source )
* AWS SDK - to embed services in our code, protected by access key

Access keys are generated from the AWS Console, one for each user (don't share between users). One the pair access ID
and Key has been generated we can log in into the CLI with the command `aws configure`.
Once the user has logged in it can perform from cli almost all the actions that are possible from the Console.

An alternative to the CLI is the `Cloud Shell` a service that is not available in all the regions but that essentially
mimic the cli in the browser.

## Pricing fundamentals

* **Compute**: you pay for computation time
* **Storage**: you pay for data stored
* **Networking**: you pay for transfer data **OUT** of the cloud (IN is free)

## Global Infrastructure

The infrastructure of AWS is divided in :

* **Regions**: are cluster of data centers and are named like us-east-1, eu-west-3; AWS services are **region scoped**
  meaning that if we use the same service on different regions it is like we are using it more than once... hence we pay
  more. `How to choose a region?` the factor to consider are:
    * **Compliance**: data might have legal requirement to resides in a particular region
    * **Proximity**: closer to the customer means less latency
    * **Available** services: not al services are available everywhere (Only the one tagged as **Global** as route 53)
    * **Pricing**: might vary from region to region
* **Availability Zones**: at least 2, max 6. usually 3, (e.g. eu-west-3a, eu-west-3b, eu-west-3c) are a group of one or
  more autonomous datacenter; they are isolated from disaster, hence if one fail the others should be available. They
  are connected with high bandwidth and low latency networking
* **Data Centers**
* **Edge Locations**

## The Share Responsibility Model

Amazon declare a shared responsibility between its services and the user; at higher level the user is responsible for
the security **IN THE CLOUD** while AWS is responsible for security **OF THE CLOUD**

<img src="./Images/SRModel.png" alt="Not Found">

# Services with `Global Scope`

Services that have a global scopes are region-independent, meaning that they can be accessed from any region.

* IAM: Identity and Access Management
* Route 53
* CloudFront

## IAM - Identity and Access Management

It used to set up users and groups in the organization, and give them permissions, through a JSON file called IAM
policy, under the Root account. Groups can only contain users and not other groups, while a user can belong to more than
one group. The **least privilege principle** is applied: don't give a user more permissions than needed.
We can also have **inline policy** that are applied to single users that don't belong to a group.

As Said, the policy is a JSON document composed by:

* Version : "2012-10-17"
* Id (optional)
* Statement (required) one or multiple, composed of:
    * Sid - Statement Identifier (optional)
    * **Effect**: **Allow** or **Deny** access
    * **Principal**: account/user/role to which the policy is applied
    * **Action**: list of actions that can or cannot be done ("*" indicates all actions)
    * **Resources**: list of resources to which the policy is applied ("*" indicates all resources)

### Policy options for Groups and Users

* AdministrationAccess: the group has administrators policy on the account
* IAMReadOnlyAccess

### Password Policy

To control the security of our organization we can set Password policy like:

* minimum password length
* required specific characters
* allow/deny for password changes or set an expiration date or prevent password re-use

Use of **MFA** Multi-Factor Authentication is strongly recommended at least for Root User. The possible MFA devices are:

* Virtual MFA like Google authenticator (only phone) or Authy (multi-device)
* Universal 2nd Factor U2F Security Key which is a physical device, like an usb key that holds the authentication token
* Hardware Key Fob MFA Device which is a physical token generator