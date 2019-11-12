

## Java bank 

### Example java bank request:

```sh
curl -H "Content-Type: application/xml"  -H "Accept: application/xml" -d  '<LoanRequest><amount>12</amount><durationInMonths>51</durationInMonths></LoanRequest>' localhost:8080
```

### Example  java bank response:

```xml
<LoanResponse>
    <loans>
        <loans>
            <amount>12.0</amount>
            <interest>4.0</interest>
            <durationInMonths>0</durationInMonths>
        </loans>
        <loans>
            <amount>12.0</amount>
            <interest>3.0</interest>
            <durationInMonths>0</durationInMonths>
        </loans>
        <loans>
            <amount>12.0</amount>
            <interest>2.0</interest>
            <durationInMonths>0</durationInMonths>
        </loans>
    </loans>
</LoanResponse>
```