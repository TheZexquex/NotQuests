/*
 * NotQuests - A Questing plugin for Minecraft Servers
 * Copyright (C) 2022 Alessio Gravili
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package rocks.gravili.notquests.paper.managers;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import rocks.gravili.notquests.paper.NotQuests;


public class MessageManager {
    private final NotQuests main;
    private final MiniMessage miniMessage;


    public final MiniMessage getMiniMessage(){
        return miniMessage;
    }

    public MessageManager(final NotQuests main){
        this.main = main;


        final TagResolver mainGradient = TagResolver.resolver("main", SimpleGradientTransformation::main);
        final TagResolver highlight = TagResolver.resolver("highlight", SimpleGradientTransformation::highlight);
        final TagResolver highlight2 = TagResolver.resolver("highlight2", SimpleGradientTransformation::highlight2);
        final TagResolver error = TagResolver.resolver("error", SimpleGradientTransformation::error);
        final TagResolver success = TagResolver.resolver("success", SimpleGradientTransformation::success);
        final TagResolver unimportant = TagResolver.resolver("unimportant", SimpleGradientTransformation::unimportant);
        final TagResolver warn = TagResolver.resolver("warn", SimpleGradientTransformation::warn);
        final TagResolver veryUnimportant = TagResolver.resolver("veryunimportant", SimpleGradientTransformation::veryUnimportant);
        final TagResolver negative = TagResolver.resolver("negative", SimpleGradientTransformation::negative);
        final TagResolver positive = TagResolver.resolver("positive", SimpleGradientTransformation::positive);

        final TagResolver tagResolver = TagResolver.builder().resolvers(
                TagResolver.standard(),
                mainGradient,
                highlight,
                highlight2,
                error,
                success,
                unimportant,
                warn,
                veryUnimportant,
                negative,
                positive
        ).build();




        //TagResolver tagResolver = TagResolver.builder().build();

        miniMessage = MiniMessage.builder().tags(tagResolver).build();
    }
}
