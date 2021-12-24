package id.aryad.cookies;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private ArrayList id, foodname, description, recipe, tools, steps;
    private Activity activity;

    public CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList foodname, ArrayList description, ArrayList recipe, ArrayList tools, ArrayList steps) {
        this.context = context;
        this.activity = activity;
        this.id = id;
        this.foodname = foodname;
        this.description = description;
        this.recipe = recipe;
        this.tools = tools;
        this.steps = steps;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rcv_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        holder.mId.setText(String.valueOf(id.get(position)));
        holder.mFoodname.setText(String.valueOf(foodname.get(position)));
        holder.mDescription.setText(String.valueOf(description.get(position)));
        holder.mRecipe.setText(String.valueOf(recipe.get(position)));
        holder.mTools.setText(String.valueOf(tools.get(position)));
        holder.mSteps.setText(String.valueOf(steps.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("foodname", String.valueOf(foodname.get(position)));
                intent.putExtra("desc", String.valueOf(description.get(position)));
                intent.putExtra("recipe", String.valueOf(recipe.get(position)));
                intent.putExtra("tools", String.valueOf(tools.get(position)));
                intent.putExtra("steps", String.valueOf(steps.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodname.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mId, mFoodname, mDescription, mRecipe, mTools, mSteps;
        public CardView mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mId = (TextView) itemView.findViewById(R.id.idRecipe);
            mFoodname = (TextView) itemView.findViewById(R.id.foodname);
            mDescription = (TextView) itemView.findViewById(R.id.description);
            mDescription.setElegantTextHeight(true);
            mDescription.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            mDescription.setSingleLine(false);
            mRecipe = (TextView) itemView.findViewById(R.id.textRecipe);
            mTools = (TextView) itemView.findViewById(R.id.textTools);
            mSteps = (TextView) itemView.findViewById(R.id.textSteps);
            mainLayout = (CardView) itemView.findViewById(R.id.rcvItem);
        }
    }
}
