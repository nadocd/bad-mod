package mod.logic;

import arc.scene.ui.layout.Table;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;
import mindustry.ui.Styles;
import mod.LCategoryExtended;

import java.util.Arrays;
import java.util.Objects;

public class NumberToStringFull {
    public static class NumberToStringInstruction implements LExecutor.LInstruction {
        public int result;
        public int num;
        public int radix;
        public NumType type;

        public NumberToStringInstruction(int result,int num,int radix,NumType type){
            this.result=result;
            this.num=num;
            this.radix=radix;
            this.type=type;
        }

        @Override
        public void run(LExecutor exec) {
            if(type.equals(NumType.Integer)){
                exec.setobj(result,Long.toString((long) exec.num(num),(int) exec.num(this.radix)));
                return;
            }
            if(type.equals(NumType.Decimal)){
                exec.setobj(result,Double.toString(exec.num(num)));
                return;
            }
            exec.setobj(result,null);
        }
    }
    public static class NumberToStringStatement extends LStatement {
        public String result="result",num="number",radix="radix";
        public NumType type=NumType.Integer;

        @Override
        public LCategory category() {
            return LCategoryExtended.string;
        }

        @Override
        public void build(Table table) {
            rebuild(table);
        }
        public void rebuild(Table table) {
            field(table,result,str->result=str);
            table.add(" = ");
            table.button(b->{
                b.label(()-> "to"+type.name()+"String(");
                b.clicked(()->{
                    showSelect(b,NumType.values(),type,(t)->{
                        this.type=t;
                        table.clearChildren();
                        rebuild(table);
                    },1,c->c.width(80f));
                });
            }, Styles.logict, ()->{}).size(165,40).color(table.color);
            table.add(" ");
            field(table,num,str->num=str);
            if(type.equals(NumType.Integer)){
                table.add(", ");
                field(table,radix,str->radix=str);
            }
            table.add(" )");
        }
        @Override
        public LExecutor.LInstruction build(LAssembler builder) {
            return new NumberToStringInstruction(builder.var(result), builder.var(num), builder.var(radix), type);
        }

        @Override
        public void write(StringBuilder builder) {
            builder.append("tostr ").append(type.text).append(" ").append(result).append(" ").append(num).append(" ").append(radix);
        }
        public static NumberToStringStatement read(String[] tokens){
            NumberToStringStatement res = new NumberToStringStatement();
            if(Objects.equals(tokens[1], "dec"))res.type=NumType.Decimal;
            else res.type=NumType.Integer;
            res.result=tokens[2];
            res.num=tokens[3];
            res.radix=tokens[4];
            return res;
        }
    }
    public enum NumType {
        Integer("int"),
        Decimal("dec");

        final String text;
        NumType(String text){
            this.text=text;
        }
    }
}
