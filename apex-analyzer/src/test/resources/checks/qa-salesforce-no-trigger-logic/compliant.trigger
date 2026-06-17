trigger AccountTrigger on Account (before insert, before update, after insert) {
    if (Trigger.isBefore) {
        if (Trigger.isInsert) {
            AccountHandler.onBeforeInsert(Trigger.new);
        }
        if (Trigger.isUpdate) {
            AccountHandler.onBeforeUpdate(Trigger.new, Trigger.oldMap);
        }
    }
    if (Trigger.isAfter && Trigger.isInsert) {
        AccountHandler.onAfterInsert(Trigger.new);
    }
}
