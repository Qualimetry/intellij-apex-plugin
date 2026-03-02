@IsTest
class ManyAssertsTest {
    @IsTest static void testEverything() {
        Account a = new Account(Name = 'Test', Industry = 'Tech');
        Assert.isNotNull(a, 'not null');
        Assert.areEqual('Test', a.Name, 'name');
        Assert.areEqual('Tech', a.Industry, 'industry');
        Assert.isTrue(a.Name.length() > 0, 'has name');
        Assert.isFalse(a.Name.contains('Bad'), 'no bad');
        Assert.areNotEqual(null, a.Industry, 'industry set');
        Assert.isTrue(a.Industry.startsWith('T'), 'starts T');
        Assert.areEqual(4, a.Name.length(), 'name length');
        Assert.isFalse(String.isBlank(a.Name), 'not blank');
        Assert.areEqual('Test', a.Name.trim(), 'trimmed');
        Assert.isTrue(a.Industry.contains('ech'), 'contains ech');
    }
}
