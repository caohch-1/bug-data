### ts-error-F18

**industrial fault description**:

Loading the product-analysis chart is erroneous
Because one key of the returned JSON data for the UI chart includes the null value

**train_ticket replicated fault description**:

When user select a train which doesn't have train food, the return result of 'getFood' will be null.
But the front stage should get the first item which represents trainfood of the result without check whether it's null.

**fault replicate steps**:

setup system:

- Use docker-compose to setup the Train-Ticket System.

fault reproduce manually step:

1. Log in
2. Change the [Terminal] Place to Tai Yuan in the [Ticket Booking] step
3. Select a date and the Train Type select [All], Click [Search]
4. There will be two searching results, click the [Booking] button of the first row whose Trip Id is [Z1234]
5. You will get the alert which says "Cannot read property '0' of null"
